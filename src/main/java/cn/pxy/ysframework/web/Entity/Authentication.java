package cn.pxy.ysframework.web.Entity;

import cn.pxy.ysframework.utils.DateTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 存储token 和用户信息
 */
@Entity
@Table(name = "Authentication")
public class Authentication implements java.io.Serializable{

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;  //id

    /**
     * 为了减少复杂程度，此处不做关系映射。
     */
    private String userId;  //用户id

    private String token;   //口令

    private String createTime;    //创建时间

    private String expireTime;    //失效时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }
}
