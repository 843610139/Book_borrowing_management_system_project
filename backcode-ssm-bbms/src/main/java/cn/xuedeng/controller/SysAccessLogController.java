package cn.xuedeng.controller;

import cn.xuedeng.common.AssembleResponseMsg;
import cn.xuedeng.model.ResponseBody;
import cn.xuedeng.service.SysAccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @功能描述：系统日志控制层
 * @Project_Name:ssm-bbms
 * @Package_Name:cn.xuedeng.controller
 * @User:徐瑞滨
 * @Date:2022/7/24 23:25
 */

@RestController
public class SysAccessLogController {
    @Autowired
    private SysAccessLogService sysAccessLogService;

    /**
        *@方法描述:查看日志列表
        *@method_Name:querySysLogList
            * @param: map
           * @return: cn.xuedeng.model.ResponseBody
        */

    @RequestMapping(value = "/querySysLogList",produces = "application/json;charset=utf-8")
    public ResponseBody querySysLogList(@RequestBody Map<String,Object> map){
        Map<String,Object> resultMap = sysAccessLogService.querySysLogList(map);
        return new AssembleResponseMsg().success(resultMap);
    }

    /**
        *@方法描述:保存日志
        *@method_Name:saveSysLog
          * @param: map
           * @return: cn.xuedeng.model.ResponseBody
        */

    @RequestMapping(value = "/saveSysLog",produces = "application/json;charset=utf-8")
    public ResponseBody saveSysLog(@RequestBody Map<String,Object> map){
        try {
            sysAccessLogService.saveSysLog(map);
            return new AssembleResponseMsg().success("ok");
        } catch (Exception e) {
            return new AssembleResponseMsg().failure(200,"error","保存日志失败");
        }
    }
}
