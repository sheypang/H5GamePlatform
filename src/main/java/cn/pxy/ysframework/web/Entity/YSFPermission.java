package cn.pxy.ysframework.web.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="YSFPermission")
public class YSFPermission implements java.io.Serializable{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String Permission;//资源权限

//    @ManyToMany(mappedBy = "ysfPermission")
//    private Set<YSFRole> ysfRoles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermission() {
        return Permission;
    }

    public void setPermission(String Resource) {
        this.Permission = Resource;
    }

//    public Set<YSFRole> getYsfRoles() {
//        return ysfRoles;
//    }
//
//    public void setYsfRoles(Set<YSFRole> ysfRoles) {
//        this.ysfRoles = ysfRoles;
//    }
}
