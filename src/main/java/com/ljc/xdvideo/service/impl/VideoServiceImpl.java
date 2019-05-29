package com.ljc.xdvideo.service.impl;

import com.ljc.xdvideo.domain.Video;
import com.ljc.xdvideo.mapper.VideoMapper;
import com.ljc.xdvideo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能描述:
 *
 * @author linjiacheng2001
 * @date 2019-02-24 13:51
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> findAll() {
        return videoMapper.findAll();
    }

    @Override
    public Video findById(int id) {
        return videoMapper.findById(id);
    }

    @Override
    public int update(Video video) {
        return videoMapper.update(video);
    }

    @Override
    public int delete(int id) {
        return videoMapper.delete(id);
    }

    @Override
    public int save(Video video) {
        int row = videoMapper.save(video);
        System.out.println("保存对象的id" + video.getId());
        return row;
    }
}
