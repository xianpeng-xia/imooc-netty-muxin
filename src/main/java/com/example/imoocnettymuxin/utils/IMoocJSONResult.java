package com.example.imoocnettymuxin.utils;

/**
 * Created by xianpeng.xia
 * on 2019-07-06 19:57
 * 200 成功
 * 500 错误
 * 501 bean验证错误
 * 502 拦截器拦截到用户token错误
 * 555 异常
 */
public class IMoocJSONResult {

    private Integer status;
    private String msg;
    private Object data;
    private String ok;

    public static IMoocJSONResult build(Integer status, String msg, Object data) {
        return new IMoocJSONResult(status, msg, data);

    }

    public static IMoocJSONResult ok(Object data) {
        return new IMoocJSONResult(data);
    }

    public static IMoocJSONResult ok() {
        return new IMoocJSONResult(null);
    }

    public static IMoocJSONResult errorMsg(Object data) {
        return new IMoocJSONResult(500, "error", data);
    }

    public static IMoocJSONResult errorMap(Object data) {
        return new IMoocJSONResult(501, "error", data);
    }

    public static IMoocJSONResult errorTokenMsg(String msg) {
        return new IMoocJSONResult(502, msg, null);
    }

    public static IMoocJSONResult errorException(String msg) {
        return new IMoocJSONResult(555, msg, null);
    }

    public IMoocJSONResult() {
    }

    public IMoocJSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public IMoocJSONResult(Object data) {
        this.status = 200;
        this.msg = "ok";
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public Boolean isOk() {
        return this.status == 200;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }
}
