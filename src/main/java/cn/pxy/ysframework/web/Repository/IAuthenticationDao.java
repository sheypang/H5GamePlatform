package cn.pxy.ysframework.web.Repository;

import cn.pxy.ysframework.web.Entity.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAuthenticationDao extends JpaRepository<Authentication,String> {
    //根据用户名id查询
    List<Authentication> findByUserId(String id);
    //根据token查询
    Authentication findByToken(String token);
    //删除证书
    void delete(Authentication authentication);
}
