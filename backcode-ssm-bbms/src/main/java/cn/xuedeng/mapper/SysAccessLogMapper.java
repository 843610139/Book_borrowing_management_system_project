package cn.xuedeng.mapper;

import java.util.List;
import java.util.Map;

/**
 * @功能描述：
 * @Project_Name:ssm-bbms
 * @Package_Name:cn.xuedeng.mapper
 * @User:徐瑞滨
 * @Date:2022/7/24 23:12
 */
public interface SysAccessLogMapper {
    //保存日志
    public int saveSysLog(Map<String,Object> map);
    //查看日志列表
    public List<Map<String,Object>> querySysLogList(Map<String,Object> map);
}
