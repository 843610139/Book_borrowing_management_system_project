package cn.xuedeng.controller;

import cn.xuedeng.common.AssembleResponseMsg;
import cn.xuedeng.model.ResponseBody;

import cn.xuedeng.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @功能描述：图书控制层
 * @Project_Name:ssm-bbms
 * @Package_Name:cn.xuedeng.controller
 * @User:徐瑞滨
 * @Date:2022/7/24 19:58
 */
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/updateInventory",produces = "application/json;charset=utf-8")
    public ResponseBody updateInventory(@RequestBody Map<String,Object> map){
        try {
            bookService.updateInventory(map);
            return new AssembleResponseMsg().success("ok");
        } catch (Exception e) {
            return new AssembleResponseMsg().failure(200,"error","更新图书库存失败");
        }
    }
    /**
        *@方法描述:查询借阅图书列表
        *@method_Name:queryBookSubList
            * @param: map
           * @return: cn.xuedeng.model.ResponseBody
        */

    @RequestMapping(value = "/queryBookSubList",produces = "application/json;charset=utf-8")
    public ResponseBody queryBookSubList(@RequestBody Map<String,Object> map){
        Map<String,Object> resultMap = bookService.queryBookSubList(map);
        return new AssembleResponseMsg().success(resultMap);
    }

    /**
        *@方法描述:归还图书
        *@method_Name:returnSubBook
           * @param: map
           * @return: cn.xuedeng.model.ResponseBody
        */

    @RequestMapping(value = "/returnSubBook",produces = "application/json;charset=utf-8")
    public ResponseBody returnSubBook(@RequestBody Map<String,Object> map){
        try {
            bookService.returnSubBook(map);
            return new AssembleResponseMsg().success("ok");
        } catch (Exception e) {
            return new AssembleResponseMsg().failure(200,"error","归还图书失败");
        }
    }
    /**
        *@方法描述:借阅图书
        *@method_Name:addSubBook
            * @param: map
            * @return: cn.xuedeng.model.ResponseBody
        */

    @RequestMapping(value = "/addSubBook",produces = "application/json;charset=utf-8")
    public ResponseBody addSubBook(@RequestBody Map<String,Object> map){
        try {
            bookService.addSubBook(map);
            return new AssembleResponseMsg().success("ok");
        } catch (Exception e) {
            return new AssembleResponseMsg().failure(200,"error","借阅图书失败");
        }
    }
    /**
        *@方法描述:查询图书列表
        *@method_Name:queryBookList
             * @param: map
             * @return: cn.xuedeng.model.ResponseBody
        */

    @RequestMapping(value = "/queryBookList",produces = "application/json;charset=utf-8")
    public ResponseBody queryBookList(@RequestBody Map<String,Object> map){
        Map<String,Object> resultMap = bookService.queryBookList(map);
        return new AssembleResponseMsg().success(resultMap);
    }

    /**
     *@方法描述:删除图书
     *@method_Name:delBook
     * @param: map
     * @return: cn.xuedeng.model.ResponseBody
     */

    @RequestMapping(value = "/delBook",produces = "application/json;charset=utf-8")
    public ResponseBody delBook(@RequestBody Map<String,Object> map){
        try {
            bookService.delBook(map);
            return new AssembleResponseMsg().success("ok");
        } catch (Exception e) {
            return new AssembleResponseMsg().failure(200,"error","删除图书失败");
        }
    }

    /**
        *@方法描述:编辑图书
        *@method_Name:editBook
           * @param: map
           * @return: cn.xuedeng.model.ResponseBody
        */

    @RequestMapping(value = "/editBook",produces = "application/json;charset=utf-8")
    public ResponseBody editBook(@RequestBody Map<String,Object> map){
        try {
            bookService.editBook(map);
            return new AssembleResponseMsg().success("ok");
        } catch (Exception e) {
            return new AssembleResponseMsg().failure(200,"error","编辑图书失败");
        }
    }

    /**
        *@方法描述:新增图书
        *@method_Name:addBook
            * @param: map
          * @return: cn.xuedeng.model.ResponseBody
        */

        @RequestMapping(value = "/addBook",produces = "application/json;charset=utf-8")
    public ResponseBody addBook(@RequestBody Map<String,Object> map){
        try {
            bookService.addBook(map);
            return new AssembleResponseMsg().success("ok");
        } catch (Exception e) {
            return new AssembleResponseMsg().failure(200,"error","新增图书失败");
        }
        }
}
