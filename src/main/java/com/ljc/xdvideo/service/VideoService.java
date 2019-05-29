package com.ljc.xdvideo.service;

import com.ljc.xdvideo.domain.Video;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 功能描述:视频业务类接口
 *
 * @author linjiacheng2001
 * @date 2019-02-22 20:49
 */
public interface VideoService {


    List<Video> findAll();


    Video findById(int id);


    int update(Video video);


    int delete(int id);

    int save(Video video);
}
