package cn.xuedeng.service.impl;

import cn.xuedeng.mapper.SysAccessLogMapper;
import cn.xuedeng.service.SysAccessLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @功能描述：系统日志业务层实现类
 * @Project_Name:backcode-ssm-bbms
 * @Package_Name:cn.xuedeng.service.impl
 * @User:徐瑞滨
 * @Date:2022/7/24 23:20
 */

@Service
public class SysAccessLogImpl implements SysAccessLogService {
    @Autowired
    private SysAccessLogMapper sysAccessLogMapper;

    /**
        *@方法描述:保存日志
        *@method_Name:saveSysLog
            * @param: map
            * @return: int
        */

    @Override
    public int saveSysLog(Map<String, Object> map) {
        return sysAccessLogMapper.saveSysLog(map);
    }

/**
    *@方法描述:查看日志列表
    *@method_Name:querySysLogList
     * @param: map
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    */

    @Override
    public Map<String, Object> querySysLogList(Map<String, Object> map) {
        //获取当前页字
        int pageNum = Integer.parseInt(map.get("pageNum").toString());
        //每页几条记录
        int pageSize = Integer.parseInt(map.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> sysLogList = sysAccessLogMapper.querySysLogList(map);
        PageInfo info = new PageInfo(sysLogList);
        long total = info.getTotal();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("total",total);
        resultMap.put("rows",sysLogList);
        return resultMap;
    }
}
