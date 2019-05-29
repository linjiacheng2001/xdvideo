package com.ljc.xdvideo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 功能描述:
 * 微信配置类
 *
 * @author linjiacheng2001
 * @date 2019-02-23 18:47
 */
@Configuration
@PropertySource(value = "classpath:application.properties")
public class WeChatConfig {

    /**
     * 公众号appId
     */
    @Value("${wxpay.appid}")
    private String appId;

    /**
     * 公众号秘钥
     */
    @Value("${wxpay.appsecret}")
    private String appsecret;

    /**
     * 开放平台appid
     */
    @Value("${wxopen.appid}")
    private String openAppid;

    /**
     * 开放平台appsecret
     */
    @Value("${wxopen.appsecret}")
    private String openAppsecret;

    /**
     * 开放平台回调url
     */
    @Value("${wxopen.redirect_url}")
    private String openRedirectUrl;

    /**
     * 微信开放平台二维码连接
     */
    private final static String OPEN_QRCODE_URL = "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";

    /**
     * 微信开放平台access_token
     *
     * @return
     */
    private final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";


    private final static String OPEN_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    /**
     * 商户id
     */
    @Value("${wxpay.mer_id}")
    private String mchId;

    /**
     * 支付key
     *
     * @return
     */
    @Value("${wxpay.key}")
    private String key;

    /**
     * 支付回调url
     *
     * @returns
     */
    @Value("${wxpay.callback}")
    private String PayCallbackUrl;


    /**
     * 统一下单url
     */
    private static final String UNIFIED_ORDER_URL = "http://api.xdclass.net/pay/unifiedorder";

    public static String getUnifiedOrderUrl() {
        return UNIFIED_ORDER_URL;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPayCallbackUrl() {
        return PayCallbackUrl;
    }

    public void setPayCallbackUrl(String payCallbackUrl) {
        PayCallbackUrl = payCallbackUrl;
    }

    public static String getOpenUserInfoUrl() {
        return OPEN_USER_INFO_URL;
    }

    public static String getAccessTokenUrl() {
        return ACCESS_TOKEN_URL;
    }

    public static String getOpenQrcodeUrl() {
        return OPEN_QRCODE_URL;
    }

    public String getOpenAppid() {
        return openAppid;
    }

    public void setOpenAppid(String openAppid) {
        this.openAppid = openAppid;
    }

    public String getOpenAppsecret() {
        return openAppsecret;
    }

    public void setOpenAppsecret(String openAppsecret) {
        this.openAppsecret = openAppsecret;
    }

    public String getOpenRedirectUrl() {
        return openRedirectUrl;
    }

    public void setOpenRedirectUrl(String openRedirectUrl) {
        this.openRedirectUrl = openRedirectUrl;
    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }
}
