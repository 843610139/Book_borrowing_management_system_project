package cn.xuedeng.controller;

import cn.xuedeng.common.AssembleResponseMsg;
import cn.xuedeng.model.ResponseBody;
import cn.xuedeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @功能描述：用户控制层
 * @Project_Name:backcode-ssm-bbms
 * @Package_Name:cn.xuedeng.controller
 * @User:徐瑞滨
 * @Date:2022/7/23 22:35
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
        *@方法描述:修改用户密码
        *@method_Name:updateUserPassword
             * @param: map
            * @return: cn.xuedeng.model.ResponseBody
        */

    @RequestMapping(value = "/updateUserPassword",produces = "application/json;charset=utf-8")
    public ResponseBody updateUserPassword(@RequestBody Map<String,Object> map){
        userService.updateUserPassword(map);
        return new AssembleResponseMsg().success("ok");
    }

    /**
        *@方法描述:查询用户列表
        *@method_Name:queryUserList
           * @param: map
             * @return: cn.xuedeng.model.ResponseBody
        */

    @RequestMapping(value = "/queryUserList",produces = "application/json;charset=utf-8")
    public ResponseBody queryUserList(@RequestBody Map<String,Object> map){
        Map<String,Object> resultMap = userService.queryUserList(map);
        return new AssembleResponseMsg().success(resultMap);
    }

    /**
        *@方法描述:根据用户id更新用户
        *@method_Name:updateUserById
             * @param: map
             * @return: cn.xuedeng.model.ResponseBody
        */

        @RequestMapping(value = "/updateUserById",produces = "application/json;charset=utf-8")
    public ResponseBody updateUserById(@RequestBody Map<String,Object> map){
        userService.updateUserById(map);
        return new AssembleResponseMsg().success("ok");
    }

    /**
        *@方法描述:根据用户姓名更新用户
        *@method_Name:updateUserByName
        * @param: map
             * @return: cn.xuedeng.model.ResponseBody
        */

        @RequestMapping(value = "/updateUserByName",produces = "application/json;charset=utf-8")
    public ResponseBody updateUserByName(@RequestBody Map<String,Object> map){
        userService.updateUserByName(map);
        return new AssembleResponseMsg().success("ok");
    }

    /**
        *@方法描述:新增用户
        *@method_Name:addUser
             * @param: map
           * @return: org.springframework.web.bind.annotation.RequestBody
        */

        @RequestMapping(value = "/addUser",produces = "application/json;charset=utf-8")
    public ResponseBody addUser(@RequestBody Map<String,Object> map){
            userService.addUser(map);
        return new AssembleResponseMsg().success("ok");
    }

    /**
        *@方法描述:根据用户姓名删除用户
        *@method_Name:delUserByUsername
         * @param: map
     * @return: cn.xuedeng.model.ResponseBody
        */

        @RequestMapping(value = "/delUserByUsername",produces = "application/json;charset=utf-8")
    public ResponseBody delUserByUsername(@RequestBody Map<String,Object> map){
        userService.delUserByUsername(map);
        return new AssembleResponseMsg().success("ok");
    }

        /**
            *@方法描述:查询用户
            *@method_Name:queryUser
                    * @param: map
                    * @return: cn.xuedeng.model.ResponseBody
            */

    @RequestMapping(value = "/queryUser",produces = "application/json;charset=utf-8")
    public ResponseBody queryUser(@RequestBody Map<String,Object> map){
        int flag = userService.queryUser(map);
        String name = "xuedeng";
        Map<String,Object> all = new HashMap<>();
        if (flag==1){
            for (Map.Entry<String,Object> entry:map.entrySet()){
                System.out.println("key: "+entry.getKey()+",value: "+entry.getValue());
                if (entry.getValue().equals(name)){
                    all.put("token","admin");
                } else {
                    all.put("token","editor");
                }
            }
            all.put("status","ok");
            return new AssembleResponseMsg().success(all);
        } else {
            return new AssembleResponseMsg().failure(200,"error","用户名或密码错误");
        }
    }
}
