package com.ljc.xdvideo.service;

import com.ljc.xdvideo.domain.VideoOrder;
import com.ljc.xdvideo.dto.VideoOrderDto;

/**
 * 功能描述:
 *
 * @author linjiacheng2001
 * @date 2019-03-14 22:56
 */
public interface VideoOrderService {

    /**
     * 下单接口
     *
     * @param videoOrderDto
     * @return
     * @throws Exception
     */
    String save(VideoOrderDto videoOrderDto) throws Exception;

    /**
     * 通过流水号查找订单
     *
     * @param outTradeNo
     * @return
     */
    VideoOrder findByOutTradeNo(String outTradeNo);

    /**
     * 通过流水号更新订单
     *
     * @param videoOrder
     * @return
     */
    int updateVideoOrderByOutTradeNo(VideoOrder videoOrder);
}
