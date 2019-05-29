package com.ljc.xdvideo.service.impl;

import com.ljc.xdvideo.config.WeChatConfig;
import com.ljc.xdvideo.domain.User;
import com.ljc.xdvideo.domain.Video;
import com.ljc.xdvideo.domain.VideoOrder;
import com.ljc.xdvideo.dto.VideoOrderDto;
import com.ljc.xdvideo.mapper.UserMapper;
import com.ljc.xdvideo.mapper.VideoMapper;
import com.ljc.xdvideo.mapper.VideoOrderMapper;
import com.ljc.xdvideo.service.VideoOrderService;
import com.ljc.xdvideo.utils.CommonUtils;
import com.ljc.xdvideo.utils.HttpUtils;
import com.ljc.xdvideo.utils.WXPayUtil;
import com.ljc.xdvideo.utils.WXPayXmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 功能描述:
 * 订单service
 *
 * @author linjiacheng2001
 * @date 2019-03-14 22:58
 */
@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Logger dataLogger = LoggerFactory.getLogger("dataLogger");

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WeChatConfig weChatConfig;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String save(VideoOrderDto videoOrderDto) throws Exception {

        dataLogger.info("module=video_order'api=save'user_id={}'video_id={}", videoOrderDto.getUserId(), videoOrderDto.getVideoId());
        //生成视频
        Video video = videoMapper.findById(videoOrderDto.getVideoId());

        //查找用户信息
        User user = userMapper.findById(videoOrderDto.getUserId());

        //生成订单
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setTotalFee(video.getPrice());
        videoOrder.setVideoImg(video.getCoverImg());
        videoOrder.setVideoTitle(video.getTitle());
        videoOrder.setCreateTime(new Date());
        videoOrder.setVideoId(video.getId());

        // 1 已支付 2 未支付
        videoOrder.setState(0);
        videoOrder.setUserId(user.getId());
        videoOrder.setHeadImg(user.getHeadImg());
        videoOrder.setNickname(user.getName());

        // 1 删除 0 未删除
        videoOrder.setDel(0);
        videoOrder.setIp(videoOrderDto.getIp());
        //设置订单流水号
        videoOrder.setOutTradeNo(CommonUtils.generateUUID());

        videoOrderMapper.insert(videoOrder);

        String codeUrl = unifiedOrder(videoOrder);
        return codeUrl;
    }

    @Override
    public VideoOrder findByOutTradeNo(String outTradeNo) {
        return videoOrderMapper.findByOutTradeNo(outTradeNo);
    }

    @Override
    public int updateVideoOrderByOutTradeNo(VideoOrder videoOrder) {
        return videoOrderMapper.updateVideoOderByOutTradeNo(videoOrder);
    }

    /**
     * 统一下单方法
     */
    public String unifiedOrder(VideoOrder videoOrder) throws Exception {

        //生成签名
        SortedMap<String, String> params = new TreeMap<>();
        params.put("appid", weChatConfig.getAppId());
        //设置商户id
        params.put("mch_id", weChatConfig.getMchId());
        params.put("nonce_str", CommonUtils.generateUUID());
        params.put("body", videoOrder.getVideoTitle());
        //商户订单号
        params.put("out_trade_no", videoOrder.getOutTradeNo());
        params.put("total_fee", videoOrder.getTotalFee().toString());
        params.put("spbill_create_ip", videoOrder.getIp());
        params.put("notify_url", weChatConfig.getPayCallbackUrl());
        params.put("trade_type", "NATIVE");
        String sign = WXPayUtil.createSign(params, weChatConfig.getKey());

        params.put("sign", sign);

        String PayXml = WXPayUtil.mapToXml(params);

        //统一下单
        //微信返回xml
        String orderStr = HttpUtils.doPost(WeChatConfig.getUnifiedOrderUrl(), PayXml, 4000);
        if (orderStr == null) {
            return null;
        }
        Map<String, String> unifiedOrderMap = WXPayUtil.xmlToMap(orderStr);
        System.out.println(unifiedOrderMap.toString());

        if (unifiedOrderMap != null) {
            return unifiedOrderMap.get("code_url");
        }
        return "";
    }

}
