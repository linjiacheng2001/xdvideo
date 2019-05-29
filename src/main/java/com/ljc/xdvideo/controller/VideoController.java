package com.ljc.xdvideo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljc.xdvideo.domain.Video;
import com.ljc.xdvideo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author linjiacheng2001
 * @date 2019-02-22 20:14
 */
@RestController
@RequestMapping("/api/v1/video")
public class VideoController {

    @Autowired
    private VideoService videoService;


    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("page")
    public Object findAll(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        List<Video> list = videoService.findAll();
        PageInfo<Video> pageInfo = new PageInfo<>(list);
        Map<String, Object> data = new HashMap<>();
        //总条数
        data.put("total_size", pageInfo.getTotal());
        //总页数
        data.put("total_page", pageInfo.getPages());
        //当前页
        data.put("current_page", page);
        //数据
        data.put("data", pageInfo.getList());
        return data;
    }

    /**
     * 根据id查找视频
     *
     * @param videoId
     * @return
     */
    @GetMapping("find_by_id")
    public Object findById(@RequestParam(value = "video_id", required = true) int videoId) {
        return videoService.findById(videoId);
    }


}
