package com.ljc.xdvideo.service.impl;

import com.ljc.xdvideo.config.WeChatConfig;
import com.ljc.xdvideo.domain.User;
import com.ljc.xdvideo.mapper.UserMapper;
import com.ljc.xdvideo.service.UserService;
import com.ljc.xdvideo.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述:
 * 用户业务服务
 *
 * @author linjiacheng2001
 * @date 2019-02-27 18:56
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private WeChatConfig weChatConfig;


    @Autowired
    private UserMapper userMapper;

    @Override
    public User saveWeChatUser(String code) {

        String accessTokenUrl = String.format(WeChatConfig.getAccessTokenUrl(), weChatConfig.getOpenAppid(), weChatConfig.getOpenAppsecret(), code);

        //获取access_token
        Map<String, Object> baseMap = HttpUtils.doGet(accessTokenUrl);
        if (baseMap == null || baseMap.isEmpty()) {
            return null;
        }
        String accessToken = (String) baseMap.get("access_token");
        String openId = (String) baseMap.get("openid");

        User user1 = userMapper.findByOpenId(openId);

        //如果存在数据库中存在这个用户
        if (user1 != null) {
            return user1;
        }
        //获取用户基本信息
        String userInfoUrl = String.format(WeChatConfig.getOpenUserInfoUrl(), accessToken, openId);
        Map<String, Object> baseUserMap = HttpUtils.doGet(userInfoUrl);
        if (baseUserMap == null || baseUserMap.isEmpty()) {
            return null;
        }
        String nickname = (String) baseUserMap.get("nickname");

        //int sex= (Integer)baseUserMap.get("sex");
        //System.out.println(sex);
        Double se = (Double) baseUserMap.get("sex");
        int b = se.intValue();
        Integer sex = Integer.valueOf(b);
        String province = (String) baseUserMap.get("province");
        String city = (String) baseUserMap.get("city");
        String country = (String) baseUserMap.get("country");
        String headimgurl = (String) baseUserMap.get("headimgurl");
        StringBuilder sb = new StringBuilder(country).append("||").append(province).append("||")
                .append(city);
        String finalAddress = sb.toString();
        try {
            nickname = new String(nickname.getBytes("ISO-8859-1"), "UTF-8");
            finalAddress = new String(finalAddress.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setName(nickname);
        user.setHeadImg(headimgurl);
        user.setCity(finalAddress);
        user.setOpenid(openId);
        user.setSex(sex);
        user.setCreateTime(new Date());
        userMapper.savaUser(user);

        return user;
    }
}
