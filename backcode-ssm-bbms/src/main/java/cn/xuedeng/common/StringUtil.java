package cn.xuedeng.common;

import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;

/**
 * @功能描述：
 * @Project_Name:ssm-bbms
 * @Package_Name:cn.xuedeng.common
 * @User:徐瑞滨
 * @Date:2022/7/24 23:49
 */
public class StringUtil {

    /**
        *@方法描述:浏览器和系统信息
        *@method_Name:getBrowserSystemInfo
          * @param: request
       * @return: java.lang.String
        */

    public static String getBrowserSystemInfo(HttpServletRequest request){
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("user-agent"));
        String BSinfo = userAgent.getOperatingSystem() + "-"+userAgent.getBrowser() + "(" + userAgent.getBrowserVersion() + ")";
        return BSinfo;
    }
}
