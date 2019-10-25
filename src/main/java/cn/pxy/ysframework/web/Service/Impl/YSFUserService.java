package cn.pxy.ysframework.web.Service.Impl;

import cn.pxy.ysframework.web.Entity.YSFUser;
import cn.pxy.ysframework.web.Repository.IYSFUserDao;
import cn.pxy.ysframework.web.Service.IYSFUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class YSFUserService implements IYSFUserService {

    @Autowired
    private IYSFUserDao userDao;

    public List findAll() {
        return userDao.findAll();
    }

    public YSFUser findUserByName(String name) {
        return userDao.findByName(name);
    }

    @Transactional
    public YSFUser add(YSFUser YSFUser) {
        return userDao.save(YSFUser);
    }

    @Override
    public YSFUser findById(String id) {
        if(userDao.findById(id).isPresent()){
            return userDao.findById(id).get();
        }
        return null;
    }
}
