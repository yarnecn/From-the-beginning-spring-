package cn.yarne.com.base.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class SysUser implements Serializable {
    private Integer id;

    /**
     * 用户真实姓名
     */
    private String username;

    private Integer age;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户状态
     */
    private Byte status;

    /**
     * 用户创建时间
     */
    private Date createtime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", createtime=" + createtime +
                '}';
    }
}