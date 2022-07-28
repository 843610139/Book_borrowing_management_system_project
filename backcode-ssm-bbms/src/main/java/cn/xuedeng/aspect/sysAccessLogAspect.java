package cn.xuedeng.aspect;

import cn.xuedeng.common.IdUtil;
import cn.xuedeng.common.StringUtil;
import cn.xuedeng.controller.SysAccessLogController;
import cn.xuedeng.service.SysAccessLogService;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @功能描述：系统访问日志切面类
 * @Project_Name:ssm-bbms
 * @Package_Name:cn.xuedeng.aspect
 * @User:徐瑞滨
 * @Date:2022/7/23 23:27
 */

@Component
public class sysAccessLogAspect {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysAccessLogService sysAccessLogService;
    //访问时间
    private Date accessDate;
    //日志信息Map
    private Map<String,Object> logMap=new HashMap<>();
    //访问类
    private Class aClass;

    /**
        *@方法描述:前置通知
        *@method_Name:doBefore
             * @param: jp
              */

    public void doBefore(JoinPoint jp){
        aClass = jp.getTarget().getClass();
        if (aClass!= SysAccessLogController.class){
            //日志Id
            logMap.put("logId", IdUtil.getUUID());
            //请求url
            String url = request.getRequestURI().toString();
            logMap.put("accessInterface",url);
            //请求Ip
            String ip = request.getRemoteAddr();
            logMap.put("accessIp", ip);
            //请求方式
            String requestType = request.getMethod();
            logMap.put("requestType", requestType);
            //访问的时间
            accessDate = new Date();
            logMap.put("accessDate", accessDate);
            //方法的参数
            String args = Arrays.toString(jp.getArgs());
            logMap.put("interfaceParams", args);
            //访问的浏览器信息
            String browserSystemInfo = StringUtil.getBrowserSystemInfo(request);
            logMap.put("accessSource", browserSystemInfo);
        }
    }

    /**
        *@方法描述:后置通知
        *@method_Name:doAfter
            * @param: jp
               */

    public void doAfter(JoinPoint jp){
        if (aClass!= SysAccessLogController.class) {
            long executeTime = new Date().getTime()-accessDate.getTime();
            //执行时长
            logMap.put("executeTime",executeTime);
            sysAccessLogService.saveSysLog(logMap);
        }
    }
}











