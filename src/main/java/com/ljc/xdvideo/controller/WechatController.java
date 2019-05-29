package com.ljc.xdvideo.controller;

import com.ljc.xdvideo.config.WeChatConfig;
import com.ljc.xdvideo.domain.JsonData;
import com.ljc.xdvideo.domain.User;
import com.ljc.xdvideo.domain.VideoOrder;
import com.ljc.xdvideo.service.UserService;
import com.ljc.xdvideo.service.VideoOrderService;
import com.ljc.xdvideo.utils.JwtUtils;
import com.ljc.xdvideo.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

/**
 * 功能描述:
 *
 * @author linjiacheng2001
 * @date 2019-02-26 14:56
 */
@Controller
@RequestMapping("/api/v1/wechat")
public class WechatController {


    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private UserService userService;

    @Autowired
    private VideoOrderService videoOrderService;


    @GetMapping("login_url")
    @ResponseBody
    public JsonData loginUrl(@RequestParam(value = "access_page", required = true) String accessPage) throws UnsupportedEncodingException {
        String redirectUrl = weChatConfig.getOpenRedirectUrl();
        String callbackUrl = URLEncoder.encode(redirectUrl, "GBK");
        String qrcodeUrl = String.format(weChatConfig.getOpenQrcodeUrl(), weChatConfig.getOpenAppid(), callbackUrl, accessPage);
        return JsonData.buildSuccess(qrcodeUrl);
    }

    @GetMapping("/user/callback")
    public void wechatUserCallback(@RequestParam(value = "code", required = true) String code, @RequestParam(value = "state") String state, HttpServletResponse response) throws IOException {
        System.out.println("cdode:" + code + "state:" + state);
        response.setCharacterEncoding("UTF-8");
        User user = userService.saveWeChatUser(code);
        System.out.println(user.toString());
        if (user != null) {

            //生成jwt
            String token = JwtUtils.geneJsonWebToken(user);
            // state 当前用户的页面地址，需要拼接 http://  这样才不会站内跳转

            response.sendRedirect(state + "?token=" + token + "&head_img=" + user.getHeadImg() + "&name=" + URLEncoder.encode(user.getName(), "UTF-8"));
        }


    }

    @RequestMapping("/order/callback")
    public void orderCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        InputStream inputStream = request.getInputStream();
        //BufferedReader包装设计模式 设计更优
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        in.close();
        inputStream.close();
        Map<String, String> callbackMap = WXPayUtil.xmlToMap(sb.toString());


        SortedMap<String, String> sortedMap = WXPayUtil.getSortedMap(callbackMap);
        //判断签名是否正确
        if (WXPayUtil.isCorrectSign(sortedMap, weChatConfig.getKey())) {
            if ("SUCCESS".equals(sortedMap.get("result_code"))) {
                String outTradeNo = sortedMap.get("out_trade_no");
                VideoOrder dbVideoOrder = videoOrderService.findByOutTradeNo(outTradeNo);
                if (dbVideoOrder != null || dbVideoOrder.getState() == 0) {
                    VideoOrder videoOrder = new VideoOrder();
                    videoOrder.setOpenid(sortedMap.get("openid"));
                    videoOrder.setOutTradeNo(outTradeNo);
                    videoOrder.setNotifyTime(new Date());
                    videoOrder.setState(1);
                    //影响行数
                    int row = videoOrderService.updateVideoOrderByOutTradeNo(videoOrder);
                    if (row == 1) {
                        response.setContentType("text/xml");
                        response.getWriter().println("success");
                        //System.out.println("支付成功");
                        return;
                    }
                }
            }

            //支付失败
            response.setContentType("text/xml");
            response.getWriter().println("fail");
        }


    }
}
