package com.ljc.xdvideo.exception;

import com.ljc.xdvideo.domain.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能描述:
 *
 * @author linjiacheng2001
 * @date 2019-03-26 22:59
 */
@ControllerAdvice
public class XdExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handler(Exception e) {

        if (e instanceof XdException) {
            XdException xdException = (XdException) e;
            return JsonData.buildError(xdException.getMsg(), xdException.getCode());
        } else {
            return JsonData.buildError("全局异常未知错误");
        }
    }
}
