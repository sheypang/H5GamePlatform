package cn.pxy.ysframework.web.Controller.Test;

import cn.pxy.ysframework.web.Entity.YSFUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/thymeleaf")
public class ThyTest {
    @GetMapping(value = "/test")
    public ModelAndView test(HttpServletRequest req) {
        // UserEntity userEntity = getCurrentUser(req);
        YSFUser user = new YSFUser();
        user.setId("1");
        user.setName("thymeleaf");
        user.setEmail("themeleaf@tf.com");
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("/page/index.html");
        return mv;
    }
    @GetMapping(value = "/hjkg")
    public ModelAndView hjkg(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/games/hjkg/index.html");
        return mv;
    }
}
