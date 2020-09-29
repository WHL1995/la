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

    private boolean status;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ResultMsg(String msg, boolean status){
        this.msg = msg;
        this.status = status;
    }

    public ResultMsg(String msg, boolean status,Object result){
        this.msg = msg;
        this.status = status;
        this.result = result;
    }

    public static ResultMsg getSuccess(String msg,Object result){
        return new ResultMsg(msg,true,result);
    }

    public static ResultMsg getSuccess(String msg){
        return new ResultMsg(msg,true);
    }

    public static ResultMsg getError(String msg){
        return new ResultMsg(msg,false);
    }
}
