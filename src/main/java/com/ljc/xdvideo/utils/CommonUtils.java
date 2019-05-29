package com.ljc.xdvideo.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 功能描述:
 * 常用工具类的封装 md5 uuid
 *
 * @author linjiacheng2001
 * @date 2019-02-22 20:50
 */
public class CommonUtils {

    /**
     * 生成uuid
     *
     * @return
     */
    public static String generateUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
        return uuid;
    }

    /**
     * md5常用工具类
     *
     * @param data
     * @return
     */
    public static String MD5(String data) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] array = md5.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                //转成16进制字符串
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            //统一转成大写
            return sb.toString().toUpperCase();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;

    }
}
