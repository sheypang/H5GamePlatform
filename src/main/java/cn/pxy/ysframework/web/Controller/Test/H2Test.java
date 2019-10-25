package cn.pxy.ysframework.web.Controller.Test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class H2Test {

    @RequestMapping("/")
    public String re() {
        return "YSFramework V1.1";
    }
}