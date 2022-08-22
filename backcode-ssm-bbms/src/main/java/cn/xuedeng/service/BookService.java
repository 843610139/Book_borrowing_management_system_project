package cn.xuedeng.service;

import cn.xuedeng.model.BookSub;

import java.util.List;
import java.util.Map;

/**
 * @功能描述：图书业务层接口
 * @Project_Name:backcode-ssm-bbms
 * @Package_Name:cn.xuedeng.service.impl
 * @User:徐瑞滨
 * @Date:2022/7/24 19:55
 */
public interface BookService {
    //新增图书
    void addBook(Map<String, Object> map);
    //编辑图书
    void editBook(Map<String, Object> map);
    //删除图书
    void delBook(Map<String, Object> map);
    //查看图书列表
    Map<String,Object> queryBookList(Map<String, Object> map);
    //借阅图书
    void addSubBook(Map<String, Object> map);
    //归还图书
    void returnSubBook(Map<String, Object> map);
    //查看借阅图书列表
    Map<String,Object> queryBookSubList(Map<String, Object> map);
    //更新图书库存
    void updateInventory(Map<String, Object> map);
}
