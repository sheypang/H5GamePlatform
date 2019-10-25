package cn.pxy.ysframework.web.Service;

import cn.pxy.ysframework.web.Entity.YSFUser;

import java.util.List;
import java.util.Set;

public interface IYSFUserService {
    List findAll();
    YSFUser findUserByName(String name);
    YSFUser add(YSFUser YSFUser);
    YSFUser findById(String id);
}
