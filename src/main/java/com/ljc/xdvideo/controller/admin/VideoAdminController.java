package com.ljc.xdvideo.controller.admin;

import com.ljc.xdvideo.domain.Video;
import com.ljc.xdvideo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述:
 *
 * @author linjiacheng2001
 * @date 2019-02-24 15:23
 */
@RestController
@RequestMapping("/admin/api/v1/video")
public class VideoAdminController {

    @Autowired
    private VideoService videoService;


    /**
     * 根据id删除视频
     *
     * @param videoId
     * @return
     */
    @DeleteMapping("del_by_id")
    public Object delById(@RequestParam(value = "video_id", required = true) int videoId) {
        return videoService.delete(videoId);
    }

    /**
     * 根据id和题目更新视频信息
     *
     * @param videoId
     * @param title
     * @return
     */
    @PutMapping("update_by_id")
    public Object update(@RequestBody Video video) {

        return videoService.update(video);
    }


    @PostMapping("save")
    public Object save(@RequestBody Video video) {

        return videoService.save(video);
    }
}
