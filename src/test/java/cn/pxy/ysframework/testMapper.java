package cn.pxy.ysframework;

import cn.pxy.ysframework.web.Entity.YSFUser;
import cn.pxy.ysframework.web.Repository.IYSFUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class testMapper {
    @Autowired
    private IYSFUserDao userDao;

    @Test
    public void findUserall(){

        System.out.print(userDao.findAll());
    }

    @Test
    public void addUser(){
        YSFUser YSFUser = new YSFUser();
        YSFUser.setPassword(DigestUtils.md5DigestAsHex(("user").getBytes()));
        YSFUser.setName("user");
        userDao.save(YSFUser);
    }

    @Test
    public void testfind(){
        List<YSFUser> YSFUser = userDao.testFind();
    }
}
