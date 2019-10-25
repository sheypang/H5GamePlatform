package cn.pxy.ysframework.web.Repository;

import cn.pxy.ysframework.web.Entity.YSFPermission;
import cn.pxy.ysframework.web.Entity.YSFUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface IYSFUserDao extends JpaRepository<YSFUser,String> {

    YSFUser findByName(String username);

    @Query(value = "select password,name from YSFUser ")
    List<YSFUser> testFind();


}
