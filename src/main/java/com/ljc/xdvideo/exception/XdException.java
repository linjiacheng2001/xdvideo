package com.ljc.xdvideo.exception;

/**
 * 功能描述:
 * 自定义异常类
 *
 * @author linjiacheng2001
 * @date 2019-03-26 22:47
 */
public class XdException extends RuntimeException {

    private int code;

    private String msg;

    public XdException() {
        super();
    }

    public XdException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
