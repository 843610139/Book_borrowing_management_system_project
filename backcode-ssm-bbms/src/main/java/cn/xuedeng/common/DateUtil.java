package cn.xuedeng.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @功能描述：日期工具类
 * @Project_Name:backcode-ssm-bbms
 * @Package_Name:cn.xuedeng.common
 * @User:徐瑞滨
 * @Date:2022/7/23 23:09
 */
public class DateUtil {
    /**
        *@方法描述:返回字符串形式的当前日期
        *@method_Name:getCurrentDateStr
     * @param: pattern 模板参数 如："yyyy-MM-dd HH:mm:ss"
     * @return: java.lang.String
        */
        public static String getCurrentDateStr(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String currentDateStr = sdf.format(new Date());
        return currentDateStr;
    }
}
