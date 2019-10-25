package cn.pxy.ysframework.web.Controller.Test;

import cn.pxy.ysframework.annotation.RequiredPermission;
import cn.pxy.ysframework.utils.*;
import cn.pxy.ysframework.web.Entity.Authentication;
import cn.pxy.ysframework.web.Entity.YSFUser;
import cn.pxy.ysframework.web.Service.IAuthenticationService;
import cn.pxy.ysframework.web.Service.IYSFUserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/User")
public class UserController {

    @Autowired
    private IYSFUserService userService;

    @Autowired
    private IAuthenticationService authenticationService;

    @GetMapping(value = "/GetAll")
    public List getAll() {
        return userService.findAll();
    }

    //    @RequiredPermission(value = "test")
    @PostMapping(value = "/Add")
    public Message add(@RequestBody @Valid YSFUser YSFUser, BindingResult result) {
        if (userService.findUserByName(YSFUser.getName()) != null) {
            return new Message("-1", "该用户已经注册");
        }
        //字段校验
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
//            for(ObjectError error:list){
//                System.out.println(error.getCode()+"—"+error.getArguments()+"—"+error.getDefaultMessage());
//            }
            return new Message("-1", list.get(0).getDefaultMessage());
        }
        YSFUser.setRegisterTime(new DateTime().toString());
        userService.add(YSFUser);
        return new Message();
    }

    @PostMapping(value = "/Login")
    public Message Login(@RequestBody YSFUser ysfUser, @Autowired HttpServletResponse response) {
        YSFUser rnUser = userService.findUserByName(ysfUser.getName());
        if (rnUser == null) {
            return new Message("601", StatusMessage.NO_USER);
        } else {
            if (rnUser.getPassword().equals(ysfUser.getPassword())) {
                //将证书放到数据库
                Authentication authentication = new Authentication();
                authentication.setUserId(rnUser.getId());
                authentication.setCreateTime(new DateTime().toString());
                //过期时间10分钟
                authentication.setExpireTime(new DateTime(10 * 1000 * 60).toString());
                final String token = DigistUtils.getUUID64();
                authentication.setToken(token);
                authenticationService.saveAuthentication(authentication);
                //跨域导致不会传递cookie
                CookieUtils.writeCookie(response, "Authorization", token);
                return new Message(token);
            }
            return new Message("602", StatusMessage.PASSWORD_ERROR);
        }
    }

    @GetMapping(value = "/GetById")
    public Message GetById(String Id) {
        YSFUser rnUser = userService.findById(Id);
        if(rnUser != null){
            return new Message(JSON.toJSONString(rnUser));
        }
        return new Message("601",StatusMessage.NO_USER);
    }

//    /****
//     * 增加一个用户
//     * @param user 用户实体类
//     * @return
//     */
//    @ApiOperation(value="添加一个用户", notes="向数据库中添加一个用户")
//    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "YSFUser", paramType = "path")
//    @PostMapping(value = "/add")
//    public int add(@RequestBody YSFUser user) {
//        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword()).getBytes()));
//        return userService.add(user);
////        int code = userService.add(user);
////        if (code == 0){
////            return new Message("0","失败");
////        }else{
////            return new Message("200");
////        }
//    }
//
//    /**
//     * 根据Id删除用户
//     * @param Id
//     * @return
//     */
//    @ApiOperation(value="根据Id删除", notes="根据Id删除用户")
//    @ApiImplicitParam(name = "Id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
//    @PostMapping(value = "/deleteById/{Id}")
//    public int deleteById(@PathVariable("Id")Integer Id){
//        return userService.deleteById(Id);
//    }
//
//    /**
//     * 修改一个用户
//     * @param user 用户实体类
//     * @return
//     */
//    @ApiOperation(value="编辑个用户", notes="编辑用户")
//    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "YSFUser", paramType = "path")
//    @PostMapping(value = "/update")
//    public int update(@RequestBody YSFUser user){
//        return userService.update(user);
//    }
//
//    /**
//     * 根据ID查询用户
//     * @param pageNum
//     * @param pageSize
//     * @return
//     */
//    @ApiOperation(value="分页查询", notes="根据页码、页面大小来分页查询用户")
//    @ApiImplicitParam(name = "Id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
//    @GetMapping(value = "/getPageList/{pageNum}/pageSize}")
//    public List<YSFUser> getPageList(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
//        return userService.getPageList(pageNum,pageSize);
//    }
//
//    /**
//     * 查询所有用户
//     * @return
//     */
//    @ApiOperation(value="查询所有", notes="查询所有用户")
//    @GetMapping(value = "/getAll")
//    public List<YSFUser> getAll(){
//        return userService.getAll();
//    }
//
//    /**
//     * 根据Id查询用户
//     * @param Id
//     * @return
//     */
//    @ApiOperation(value="根据Id查询", notes="根据Id查询用户")
//    @ApiImplicitParam(name = "Id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
//    @GetMapping(value = "/getById/{Id}")
//    public YSFUser getById(@PathVariable("Id")Integer Id){
//        return userService.getById(Id);
//    }
//
//
//    /**
//     * 根据Id查询用户
//     * @param Id
//     * @return
//     */
//    @ApiOperation(value="根据Id查询", notes="根据Id查询用户")
//    @ApiImplicitParam(name = "Id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
//    @GetMapping(value = "/getInfo/{Id}")
//    public  List<Map<String,String>> getinfo(@PathVariable("Id")Integer Id){
//        return userService.getinfo(Id);
//    }

}
