package cn.xuedeng.service.impl;

import cn.xuedeng.mapper.BookMapper;
import cn.xuedeng.model.Book;
import cn.xuedeng.model.BookSub;
import cn.xuedeng.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @功能描述：图书业务层实现类
 * @Project_Name:backcode-ssm-bbms
 * @Package_Name:cn.xuedeng.service.impl
 * @User:徐瑞滨
 * @Date:2022/7/24 19:56
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    /**
        *@方法描述:添加图书
        *@method_Name:addBook
             * @param: map
                */

    @Override
    public void addBook(Map<String, Object> map) {
        bookMapper.addBook(map);
    }

    /**
        *@方法描述:编辑图书
        *@method_Name:editBook
             * @param: map
              */

    @Override
    public void editBook(Map<String, Object> map) {
        bookMapper.editBook(map);
    }

    /**
        *@方法描述:删除图书
        *@method_Name:delBook
           * @param: map
               */

    @Override
    public void delBook(Map<String, Object> map) {
        bookMapper.delBook(map);
    }

    /**
        *@方法描述:查询图书列表
        *@method_Name:queryBookList
             * @param: map
            * @return: java.util.Map<java.lang.String,java.lang.Object>
        */

    @Override
    public Map<String, Object> queryBookList(Map<String, Object> map) {
        //获取当前页字
        int pageNum = Integer.parseInt(map.get("pageNum").toString());
        //每页几条记录
        int pageSize = Integer.parseInt(map.get("pageSize").toString());
        PageHelper.startPage(pageNum,pageSize);
        List<Book> bookList = bookMapper.queryBookList(map);
        PageInfo<Book> info = new PageInfo<Book>(bookList);
        long total = info.getTotal();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("total",total);
        resultMap.put("rows",bookList);
        return resultMap;
    }

    /**
        *@方法描述:借阅图书
        *@method_Name:addSubBook
            * @param: map
                */

    @Override
    public void addSubBook(Map<String, Object> map) {
            bookMapper.addSubBook(map);
    }

    /**
        *@方法描述:归还图书
        *@method_Name:returnSubBook
            * @param: map
              */

    @Override
    public void returnSubBook(Map<String, Object> map) {
        bookMapper.returnSubBook(map);
    }

    /**
        *@方法描述:查看借阅图书列表
        *@method_Name:queryBookSubList
             * @param: map
            * @return: java.util.Map<java.lang.String,java.lang.Object>
        */

    @Override
    public Map<String, Object> queryBookSubList(Map<String, Object> map) {
            //获取当前页字
            int pageNum = Integer.parseInt(map.get("pageNum").toString());
            //每页几条记录
            int pageSize = Integer.parseInt(map.get("pageSize").toString());
            PageHelper.startPage(pageNum,pageSize);
            List<BookSub> bookSubList = bookMapper.queryBookSubList(map);
        for (BookSub a:bookSubList
             ) {
            System.out.println("a = " + a);
        }
            PageInfo<BookSub> info = new PageInfo<BookSub>(bookSubList);
            long total = info.getTotal();
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("total",total);
            resultMap.put("rows",bookSubList);
            return resultMap;
        }

    /**
        *@方法描述:更新图书库存
        *@method_Name:updateInventory
             * @param: map
                */


    @Override
    public void updateInventory(Map<String, Object> map) {
        bookMapper.updateInventory(map);
    }
}

