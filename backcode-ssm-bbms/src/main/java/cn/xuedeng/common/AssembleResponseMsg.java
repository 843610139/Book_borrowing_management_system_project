package cn.xuedeng.common;

import cn.xuedeng.model.InfoMsg;
import cn.xuedeng.model.ResponseBody;


import java.util.Map;

/**
 * @功能描述：封装ResponseBody内容
 * @Project_Name:backcode-ssm-bbms
 * @Package_Name:cn.xuedeng.common
 * @User:徐瑞滨
 * @Date:2022/7/23 22:51
 */
public class AssembleResponseMsg {
    /**
        *@方法描述:操作成功，返回内容
        *@method_Name:success
     * @param: data
     * @return: org.springframework.web.bind.annotation.ResponseBody
        */
    public <T> ResponseBody success(T data) {
        ResponseBody<T> resp = new ResponseBody<T>();
        InfoMsg infoMsg = new InfoMsg();
        resp.setData(data);
        resp.setInfoMsg(infoMsg);
        return resp;
    }
    /**
        *@方法描述:操作失败返回内容
        *@method_Name:failure
     * @param: status
     * @param: errorCode
     * @param: message
     * @return: org.springframework.web.bind.annotation.ResponseBody
        */
    public <T>ResponseBody failure(int status,String errorCode,String message) {
        ResponseBody<T> resp = new ResponseBody<T>();
        resp.setStatus(status);
        InfoMsg info = new InfoMsg();
        info.setCode(errorCode);
        info.setMessage(message);
        resp.setInfoMsg(info);
        return resp;
    }
}
