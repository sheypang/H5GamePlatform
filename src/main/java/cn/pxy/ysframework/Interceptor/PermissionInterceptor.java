package cn.pxy.ysframework.Interceptor;

import cn.pxy.ysframework.annotation.RequiredPermission;
import cn.pxy.ysframework.web.Entity.YSFPermission;
import cn.pxy.ysframework.web.Service.IAuthenticationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private IAuthenticationService authenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 验证权限
        if (this.hasPermission(handler,request)) {
            return true;
        }
        //  null == request.getHeader("x-requested-with") TODO 暂时用这个来判断是否为ajax请求
        // 如果没有权限 则抛401异常 springboot会处理
        response.sendError(HttpStatus.UNAUTHORIZED.value(), "无权限");
        return false;
    }

    /**
     * 是否有权限
     *
     * @param handler
     * @return
     */
    private boolean hasPermission(Object handler,HttpServletRequest request) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            RequiredPermission requiredPermission = handlerMethod.getMethod().getAnnotation(RequiredPermission.class);
            // 如果方法上的注解为空 则获取类的注解
            if (requiredPermission == null) {
                requiredPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequiredPermission.class);
            }
            // 如果标记了注解，则判断权限
            if (requiredPermission != null && StringUtils.isNotBlank(requiredPermission.value())) {
                // redis或数据库 中获取该用户的权限信息 并判断是否有权限
                String token = request.getHeader("Authorization");
                //token为空
                if (StringUtils.isBlank(token)){
                    return false;
                }
                Set<YSFPermission> permissionSet = authenticationService.getPermissionByAuthenticationToken(token);
                Set<String> signSet = new HashSet();
                for(YSFPermission permission : permissionSet){
                    signSet.add(permission.getPermission());
                }
                if (CollectionUtils.isEmpty(permissionSet) ){
                    return false;
                }
                return signSet.contains(requiredPermission.value());
            }
        }
        return true;
    }
}
