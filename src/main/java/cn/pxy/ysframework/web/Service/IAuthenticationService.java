package cn.pxy.ysframework.web.Service;

import cn.pxy.ysframework.web.Entity.Authentication;
import cn.pxy.ysframework.web.Entity.YSFPermission;

import java.util.Set;


public interface IAuthenticationService {

    //根据token获取证书
    Authentication getAuthenticationByToken(String Token);

    //根据证书的token获取权限集合
    Set<YSFPermission> getPermissionByAuthenticationToken(String token);

    //保存证书
    void saveAuthentication(Authentication authentication);
}
