package cn.xuedeng.service;

import java.util.Map;

/**
 * @功能描述：用户业务层接口
 * @Project_Name:backcode-ssm-bbms
 * @Package_Name:cn.xuedeng.service.impl
 * @User:徐瑞滨
 * @Date:2022/7/23 22:05
 */
public interface UserService {
    //查询用户
    int queryUser(Map<String, Object> map);
    //新增用户
    void addUser(Map<String, Object> map);
    //根据用户姓名更新用户
    void updateUserByName(Map<String, Object> map);
    //根据用户id更新用户
    void updateUserById(Map<String, Object> map);
    //根据用户姓名删除用户
    void delUserByUsername(Map<String, Object> map);
    //查询用户列表
    Map<String,Object> queryUserList(Map<String, Object> map);
    //修改用户密码
    void updateUserPassword(Map<String, Object> map);
}
