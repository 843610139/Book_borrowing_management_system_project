package cn.xuedeng.model;

import java.io.Serializable;

/**
 * @功能描述：用户实体类
 * @Project_Name:backcode-ssm-bbms
 * @Package_Name:cn.xuedeng.model
 * @User:徐瑞滨
 * @Date:2022/7/23 23:22
 */
public class User implements Serializable {
    private int id;
    private String uId; //用户编号
    private String userName; //用户名
    private String passWord;//密码
    private String uName;//姓名
    private Integer age;//年龄
    private Integer sex;//性别

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uId='" + uId + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", uName='" + uName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
