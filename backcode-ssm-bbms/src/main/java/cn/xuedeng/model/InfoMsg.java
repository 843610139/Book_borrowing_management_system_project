package cn.xuedeng.model;

import java.io.Serializable;

/**
 * @功能描述：错误信息消息体
 * @Project_Name:backcode-ssm-bbms
 * @Package_Name:cn.xuedeng.model
 * @User:徐瑞滨
 * @Date:2022/7/23 23:02
 */
public class InfoMsg implements Serializable {
    //自定义错误码 默认0表示正常执行
    private String code = "0";
    //错误信息
    private String message = "操作成功";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
