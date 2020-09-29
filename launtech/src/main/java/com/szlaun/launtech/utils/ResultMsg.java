package com.szlaun.launtech.utils;

/**
 * @Description 统一响应类
 * @Author lizhiming
 * @Date 2020/9/29 13:47
 * @Version V1.0
 **/
public class ResultMsg {
    private Object result;

    private String msg;

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultMsg(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public ResultMsg(String msg, int code, Object result) {
        this.msg = msg;
        this.code = code;
        this.result = result;
    }

    public static ResultMsg getSuccess(String msg, Object result) {
        return new ResultMsg(msg, 200, result);
    }

    public static ResultMsg getSuccess(String msg) {
        return new ResultMsg(msg, 200);
    }

    public static ResultMsg getError(String msg) {
        return new ResultMsg(msg, 500);
    }

    public static ResultMsg getSuccess() {
        return new ResultMsg("操作成功", 200);
    }

    public static ResultMsg getError() {
        return new ResultMsg("操作失败", 500);
    }
}
