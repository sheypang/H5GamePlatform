package cn.pxy.ysframework.web.Controller.Test;

import cn.pxy.ysframework.annotation.RequiredPermission;
import cn.pxy.ysframework.utils.Message;
import cn.pxy.ysframework.utils.ResponseBean;
import cn.pxy.ysframework.web.Service.IYSFUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private IYSFUserService userService;

//    @PostMapping("/login")
//    public ResponseBean login(@RequestBody YSFUser user) {
//        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
//        YSFUser YSFUser = userService.findUserByName(user.getName());
//        if (YSFUser.getPassword().equals(user.getPassword())) {
//            return new ResponseBean(200, "登陆成功", JWTUtil.sign(user.getName(), user.getPassword()));
//        } else {
//            throw new UnauthorizedException();
//        }
//    }


    @PostMapping(value = "/test2")
    public Message test2(){
        return new Message("rererererre");
    }

    @RequiredPermission("test")
    @GetMapping(value = "/test3")
    public Message test3(){
        return new Message("rererererre");
    }


    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean unauthorized() {
        return new ResponseBean(401, "Unauthorized", null);
    }
}
