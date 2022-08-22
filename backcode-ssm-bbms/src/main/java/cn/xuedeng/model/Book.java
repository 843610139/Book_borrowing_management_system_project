package cn.xuedeng.model;

/**
 * @功能描述：图书实体类
 * @Project_Name:backcode-ssm-bbms
 * @Package_Name:cn.xuedeng.model
 * @User:徐瑞滨
 * @Date:2022/7/24 21:11
 */
public class Book {
    private int bId;
    private String bookName; //图书名
    private String author; //作者名
    private int inventory;//库存量
    private double price;//价格

    @Override
    public String toString() {
        return "Book{" +
                "bId=" + bId +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", inventory=" + inventory +
                ", price=" + price +
                '}';
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
