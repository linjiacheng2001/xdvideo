package com.ljc.xdvideo.service;

import com.ljc.xdvideo.domain.User;

/**
 * 功能描述:用户业务
 *
 * @author linjiacheng2001
 * @date 2019-02-27 18:55
 */
public interface UserService {

    User saveWeChatUser(String code);

}
