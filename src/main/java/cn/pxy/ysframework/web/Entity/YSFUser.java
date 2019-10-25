package cn.pxy.ysframework.web.Entity;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "YSFUser")
public class YSFUser implements java.io.Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotEmpty(message = "姓名不能为空")
    private String name;//用户名

    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, message = "密码长度不能小于6位")
    private String password;//密码

    @ManyToOne(fetch = FetchType.EAGER)
    private YSFRole ysfrole;//角色

    private String registerTime;//注册时间

    private String isActive;//是否激活  1激活,0未激活

    private String status;//是否启用 1启用,0禁用

    /**
     * 验证码不做持久化对象
     */
    @Transient
    private String vaildateCode;

    public String getVaildateCode() {
        return vaildateCode;
    }

    public void setVaildateCode(String vaildateCode) {
        this.vaildateCode = vaildateCode;
    }

    @Email
    private String email;//邮箱

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public YSFRole getYsfrole() {
        return ysfrole;
    }

    public void setYsfrole(YSFRole ysfrole) {
        this.ysfrole = ysfrole;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String IsActive) {
        this.isActive = IsActive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}