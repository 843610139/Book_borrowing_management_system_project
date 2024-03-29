package cn.xuedeng.model;

import cn.xuedeng.common.DateUtil;

import java.io.Serializable;

/**
 * @功能描述：封装响应的数据结构
 * @Project_Name:backcode-ssm-bbms
 * @Package_Name:cn.xuedeng.model
 * @User:徐瑞滨
 * @Date:2022/7/23 23:06
 */
public class ResponseBody<T> implements Serializable {
    //时间
    private String date= DateUtil.getCurrentDateStr("yyyy-MM-dd HH:mm:ss");
    //状态码 默认200响应成功
    private  int status=200;
    //接口返回的数据
    private T data;
    //消息内容
    private InfoMsg infoMsg;

    public InfoMsg getInfoMsg() {
        return infoMsg;
    }

    public void setInfoMsg(InfoMsg infoMsg) {
        this.infoMsg = infoMsg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
