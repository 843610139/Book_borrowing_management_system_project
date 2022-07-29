package cn.xuedeng.mapper;

import cn.xuedeng.model.Book;
import cn.xuedeng.model.BookSub;

import java.util.List;
import java.util.Map;

/**
 * @功能描述：图书持久层接口
 * @Project_Name:ssm-bbms
 * @Package_Name:cn.xuedeng.mapper
 * @User:徐瑞滨
 * @Date:2022/7/24 19:49
 */
public interface BookMapper {
    //新增图书
    void addBook(Map<String,Object> map);
    //编辑图书
    void editBook(Map<String,Object> map);
    //删除图书
    void delBook(Map<String,Object> map);
    //查看图书列表
    List<Book> queryBookList(Map<String,Object> map);
    //借阅图书
    void addSubBook(Map<String,Object> map);
    //归还图书
    void returnSubBook(Map<String,Object> map);
    //查看借阅图书列表
    List<BookSub> queryBookSubList(Map<String,Object> map);
    //更新图书库存
    void updateInventory(Map<String,Object> map);
}
