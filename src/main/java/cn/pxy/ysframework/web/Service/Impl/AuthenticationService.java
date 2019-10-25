package cn.pxy.ysframework.web.Service.Impl;

import cn.pxy.ysframework.web.Entity.Authentication;
import cn.pxy.ysframework.web.Entity.YSFPermission;
import cn.pxy.ysframework.web.Entity.YSFUser;
import cn.pxy.ysframework.web.Repository.IAuthenticationDao;
import cn.pxy.ysframework.web.Repository.IYSFUserDao;
import cn.pxy.ysframework.web.Service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 口令Service
 */
@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private IAuthenticationDao authenticationDao;

    @Autowired
    private IYSFUserDao userDao;

    @Override
    public Authentication getAuthenticationByToken(String Token) {
        return authenticationDao.findByToken(Token);
    }

    //根据token返回权限集合
    //如果Authentication过期了那么就要删除该条记录
    @Override
    //不开启事物会出异常 导致user无法加载
    @Transactional
    public Set<YSFPermission> getPermissionByAuthenticationToken(String token) {
        Authentication authentication = authenticationDao.findByToken(token);
        //如果查询到有该证书
        if (authentication!=null){
            //获取当前时分秒
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String now = sdf.format(new Date());
            //证书过期，删除该字段
            if(authentication.getExpireTime().compareTo(now)<0){
                authenticationDao.delete(authentication);
                return null;
            }
//        String userId = authenticationDao.findByToken(token).getUserId();
//            YSFUser ysfUser = userDao.findById(authentication.getUserId()).get();
//            if (authenticationDao.findByUserId(ysfUser.getId()).size()>=1){
//                //删除该用户之前的证书
//                List<Authentication> authenticationList = authenticationDao.findByUserId(ysfUser.getId());
//                for(Authentication auth:authenticationList){
//                    authenticationDao.delete(auth);
//                }
//            }
            return userDao.findById(authentication.getUserId()).get().getYsfrole().getYsfPermission();
        }
       return null;
    }

    /**
     * 删除该用户之前的证书，然后再保存。
     * @param authentication
     */
    @Override
    @Transactional
    public void saveAuthentication(Authentication authentication) {
        List<Authentication> list = authenticationDao.findByUserId(authentication.getUserId());
        if (list.size()>0){
            for(Authentication auth : list){
                authenticationDao.delete(auth);
            }
        }
        authenticationDao.save(authentication);
    }
}
