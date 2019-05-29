package com.ljc.xdvideo.mapper;

import com.ljc.xdvideo.domain.VideoOrder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * 功能描述:
 *
 * @author linjiacheng2001
 * @date 2019-03-08 22:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoOrderMapperTest {

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Test
    public void insert() {
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setCreateTime(new Date());
        videoOrder.setDel(0);
        videoOrder.setNickname("xxxxaaaa");
        videoOrder.setDel(0);
        videoOrderMapper.insert(videoOrder);
        Assert.assertNotNull(videoOrder.getId());

    }

    @Test
    public void findById() {
        VideoOrder videoOrder = videoOrderMapper.findById(1);
        Assert.assertNotNull(videoOrder);
    }

    @Test
    public void findByOutTradeNo() {
    }

    @Test
    public void del() {
    }

    @Test
    public void findMyOrders() {
    }

    @Test
    public void updateVideoOrderByOutTradeNo() {
    }
}