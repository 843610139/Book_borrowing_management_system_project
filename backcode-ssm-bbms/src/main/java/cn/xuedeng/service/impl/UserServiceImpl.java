package cn.xuedeng.service.impl;

import cn.xuedeng.mapper.UserMapper;
import cn.xuedeng.model.User;
import cn.xuedeng.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @功能描述：用户业务层实现类
 * @Project_Name:backcode-ssm-bbms
 * @Package_Name:cn.xuedeng.service.impl
 * @User:徐瑞滨
 * @Date:2022/7/23 22:07
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
        *@方法描述:根据条件查询用户
        *@method_Name:queryUser
     * @param: map
     * @return: int
        */

    @Override
    public int queryUser(Map<String, Object> map) {
        return userMapper.queryUser(map);
    }

    /**
        *@方法描述:新增用户
        *@method_Name:addUser
             * @param: map
           * @return: int
        */

    @Override
    public void addUser(Map<String, Object> map) {
            userMapper.addUser(map);
    }


    /**
        *@方法描述:根据用户姓名更新用户
        *@method_Name:updateUserByName
             * @param: map
                */
    @Override
    public void updateUserByName(Map<String, Object> map) {
        userMapper.updateUserByName(map);
    }

    /**
        *@方法描述:根据用户id更新用户
        *@method_Name:updateUserById
            * @param: map
                */

    @Override
    public void updateUserById(Map<String, Object> map) {
        userMapper.updateUserById(map);
    }

    /**
        *@方法描述:根据用户姓名删除用户
        *@method_Name:delUserByUsername
     * @param: map
        */
    @Override
    public void delUserByUsername(Map<String, Object> map) {
        userMapper.delUserByUsername(map);
    }

    /**
        *@方法描述:查询用户列表
        *@method_Name:queryUserList
            * @param: map
            * @return: java.util.Map<java.lang.String,java.lang.Object>
        */
    @Override
    public Map<String, Object> queryUserList(Map<String, Object> map) {
        //获取当前页字
        int pageNum = Integer.parseInt(map.get("pageNum").toString());
        //每页几条记录
        int pageSize = Integer.parseInt(map.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = userMapper.queryUserList(map);
        PageInfo info = new PageInfo();
        long total = info.getTotal();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("total",total);
        resultMap.put("rows",userList);
        return resultMap;
    }

    /**
        *@方法描述:修改用户密码
        *@method_Name:updateUserPassword
           * @param: map
                */

    @Override
    public void updateUserPassword(Map<String, Object> map) {
        userMapper.updateUserPassword(map);
    }
}
