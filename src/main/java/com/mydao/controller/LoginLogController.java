package main.java.com.mydao.controller;

import main.java.com.mydao.service.LoginLogService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    @RequestMapping("/hello")
    public String hello(@PathVariable Long recId){
        return loginLogService.selectByPrimaryKey(1L).getStaffId();
    }
}
