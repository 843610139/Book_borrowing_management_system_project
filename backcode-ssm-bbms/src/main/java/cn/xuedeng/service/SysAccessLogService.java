package cn.xuedeng.service;

import java.util.List;
import java.util.Map;

/**
 * @功能描述：
 * @Project_Name:ssm-bbms
 * @Package_Name:cn.xuedeng.service.impl
 * @User:徐瑞滨
 * @Date:2022/7/24 23:19
 */
public interface SysAccessLogService {
    //保存日志
    public int saveSysLog(Map<String,Object> map);
    //查看日志列表
    public Map<String,Object> querySysLogList(Map<String,Object> map);
}
