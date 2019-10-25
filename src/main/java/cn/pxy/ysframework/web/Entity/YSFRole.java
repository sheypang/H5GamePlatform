package cn.pxy.ysframework.web.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="YSFRole")
public class YSFRole implements java.io.Serializable{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String name;//角色名称

    @OneToMany(mappedBy = "ysfrole",fetch = FetchType.EAGER)
    private Set<YSFUser> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns={@JoinColumn(name="YSFRole_ID")},
            inverseJoinColumns={@JoinColumn(name="YSFPermission_ID")})
    private Set<YSFPermission> ysfPermission = new HashSet();

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

    public Set<YSFUser> getUsers() {
        return users;
    }

    public void setUsers(Set<YSFUser> users) {
        this.users = users;
    }

    public Set<YSFPermission> getYsfPermission() {
        return ysfPermission;
    }

    public void setYsfPermission(Set<YSFPermission> ysfPermission) {
        this.ysfPermission = ysfPermission;
    }
}

