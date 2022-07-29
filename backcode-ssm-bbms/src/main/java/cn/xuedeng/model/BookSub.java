package cn.xuedeng.model;

import java.util.Date;

/**
 * @功能描述：用户借书记录
 * @Project_Name:backcode-ssm-bbms
 * @Package_Name:cn.xuedeng.model
 * @User:徐瑞滨
 * @Date:2022/7/24 21:34
 */
public class BookSub {
    private int id;
    private int uId;//用户ID
    private int bId;//图书ID
    private String subDateTime;//借阅时间
    private String returnDateTime;//归还时间
    private int bookType;//图书状态
    private int returnType;//归还状态

    @Override
    public String toString() {
        return "BookSub{" +
                "id=" + id +
                ", uId=" + uId +
                ", bId=" + bId +
                ", subDateTime='" + subDateTime + '\'' +
                ", returnDateTime='" + returnDateTime + '\'' +
                ", bookType=" + bookType +
                ", returnType=" + returnType +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public String getSubDateTime() {
        return subDateTime;
    }

    public void setSubDateTime(String subDateTime) {
        this.subDateTime = subDateTime;
    }

    public String getReturnDateTime() {
        return returnDateTime;
    }

    public void setReturnDateTime(String returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

    public int getBookType() {
        return bookType;
    }

    public void setBookType(int bookType) {
        this.bookType = bookType;
    }

    public int getReturnType() {
        return returnType;
    }

    public void setReturnType(int returnType) {
        this.returnType = returnType;
    }
}
