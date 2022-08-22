package cn.xuedeng.common;

import java.util.UUID;

/**
 * @功能描述：各种Id工具类
 * @Project_Name:backcode-ssm-bbms
 * @Package_Name:cn.xuedeng.common
 * @User:徐瑞滨
 * @Date:2022/7/24 23:39
 */

public class IdUtil {
    
    /**
        *@方法描述:获取uuid(以去掉'-'字符)
        *@method_Name:getUUID
        *@param:
        *@return:
     * @return: java.lang.String
        */
        
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
