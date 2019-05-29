package com.ljc.xdvideo.service.impl;

import com.ljc.xdvideo.domain.Video;
import com.ljc.xdvideo.service.VideoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * 功能描述:
 *
 * @author linjiacheng2001
 * @date 2019-03-08 22:18
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoServiceImplTest {

    @Autowired
    private VideoService videoService;

    @Test
    public void findAll() {
        List<Video> list = videoService.findAll();
        Assert.assertNotNull(list);
    }

    @Test
    public void findById() {

        Video video = videoService.findById(2);
        Assert.assertNotNull(video);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void save() {
    }
}