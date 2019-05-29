package com.ljc.xdvideo.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.ljc.xdvideo.domain.JsonData;
import com.ljc.xdvideo.dto.VideoOrderDto;
import com.ljc.xdvideo.service.VideoOrderService;
import com.ljc.xdvideo.utils.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述:
 * 订单接口
 *
 * @author linjiacheng2001
 * @date 2019-03-01 15:40
 */
@RestController
@RequestMapping("/user/api/v1/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Logger dataLogger = LoggerFactory.getLogger("dataLogger");


    @Autowired
    private VideoOrderService videoOrderService;

    @GetMapping("add")
    public void saveOrder(@RequestParam(value = "video_id", required = true) int videoId,
                          HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ip = IpUtils.getIpAddr(request);
        //String ip="120.25.1.43";
        int userId = 1;
        VideoOrderDto videoOrderDto = new VideoOrderDto();
        videoOrderDto.setUserId(userId);
        videoOrderDto.setVideoId(videoId);
        videoOrderDto.setIp(ip);
        String orderUrl = videoOrderService.save(videoOrderDto);

        //将code_url转成生成二维码
        //return JsonData.buildSuccess("下单成功");
        if (orderUrl == null) {
            throw new NullPointerException();
        }
        try {
            //生成二维码配置
            Map<EncodeHintType, Object> hints = new HashMap<>();
            //设置纠错等级
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            //编码类型
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(orderUrl, BarcodeFormat.QR_CODE, 400, 400, hints);
            OutputStream out = response.getOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
