package com.ljc.xdvideo.controller;

import com.ljc.xdvideo.config.WeChatConfig;
import com.ljc.xdvideo.domain.JsonData;
import com.ljc.xdvideo.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述:
 *
 * @author linjiacheng2001
 * @date 2019-02-22 20:14
 */
@RestController
public class TestController {


    @Autowired
    private WeChatConfig weChatConfig;


    @RequestMapping("test_config")
    public JsonData testConfig() {
        System.out.println(weChatConfig.getAppId());
        return JsonData.buildSuccess(200, weChatConfig.getAppId(), "测试成功");

    }


    @Autowired
    private VideoMapper videoMapper;

    @RequestMapping("test_db")
    public Object testDb() {
        return videoMapper.findAll();

    }


}
