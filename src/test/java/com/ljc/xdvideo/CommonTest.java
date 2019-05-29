package com.ljc.xdvideo;

import com.ljc.xdvideo.domain.User;
import com.ljc.xdvideo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.Test;

/**
 * 功能描述:
 *
 * @author linjiacheng2001
 * @date 2019-02-25 14:59
 */
public class CommonTest {

    @Test
    public void testGeneJwt() {
        User user = new User();
        user.setId(999);
        user.setName("xdclass");
        user.setHeadImg("www.baidu.com");
        String token = JwtUtils.geneJsonWebToken(user);
        System.out.println(token);
    }


    @Test
    public void testCheck() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4ZGNsYXNzIiwiaWQiOjk5OSwibmFtZSI6InhkY2xhc3MiLCJpbWciOiJ3d3cuYmFpZHUuY29tIiwiaWF0IjoxNTUxMDc4NTg3LCJleHAiOjE1NTE2ODMzODd9.Hr94GSrd5d_kQRbeFtcgBZmdJM2Hgo8qSS97ZnZY78I";
        Claims claims = JwtUtils.checkToken(token);
        if (claims != null) {
            int id = (Integer) claims.get("id");
            String name = (String) claims.get("name");
            String img = (String) claims.get("img");
            System.out.println("id:" + id + "name:" + name + "img:" + img);
        } else {
            System.out.println("非法token");
        }
    }
}
