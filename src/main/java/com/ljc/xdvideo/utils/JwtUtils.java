package com.ljc.xdvideo.utils;

import com.ljc.xdvideo.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * 功能描述:
 * jwt工具类
 *
 * @author linjiacheng2001
 * @date 2019-02-25 14:30
 */
public class JwtUtils {

    public static final String SUBJECT = "xdclass";

    public static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;

    public static final String APPSECRET = "xd666";

    /**
     * 生成jwt
     *
     * @param user
     * @return
     */
    public static String geneJsonWebToken(User user) {
        if (user == null || user.getId() == null || user.getName() == null || user.getHeadImg() == null) {
            return null;
        }

        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("img", user.getHeadImg())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET).compact();
        return token;

    }


    /**
     * 校验token
     *
     * @param token
     * @return
     */
    public static Claims checkToken(String token) {

        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET)
                    .parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {

        }
        return null;
    }


}
