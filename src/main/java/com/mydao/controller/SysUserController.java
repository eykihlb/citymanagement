package main.java.com.mydao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.mydao.entity.SysUser;
import main.java.com.mydao.model.AccessConfig;
import main.java.com.mydao.service.LoginLogService;
import main.java.com.mydao.service.SysUserService;
import main.java.com.mydao.utils.ElectronicSignatureUtil;
import main.java.com.mydao.utils.IPUtils;
import main.java.com.mydao.utils.JsonUtil;
import main.java.com.mydao.utils.Md5Util;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private AccessConfig accessConfig;

    private ObjectMapper om = new ObjectMapper();

    @RequestMapping("/userLogin")
    public String hello(@RequestParam String param,HttpSession session,HttpServletRequest request) throws Exception{//@RequestParam String accessToken, @RequestParam String timeStamp, @RequestParam String data
        Map<String,Object> paramMap = JsonUtil.jsontoHashMap(param);
        String accessToken = paramMap.get("accessToken").toString();
        String timeStamp = paramMap.get("timeStamp").toString();
        Map<String,Object> paramMap1 = JsonUtil.jsontoHashMap(paramMap.get("data").toString());
        Map<String,Object> resultMap = new HashMap<>();
        SysUser su = sysUserService.userLogin(paramMap1);
        String a = Md5Util.MD5Encode(timeStamp+accessConfig.getSK());
        String b = accessConfig.getAK()+timeStamp+Md5Util.MD5Encode(timeStamp+accessConfig.getSK());
        System.out.println(IPUtils.getIpAddress(request));
        if (accessToken.equals(accessConfig.getAK()+timeStamp+Md5Util.MD5Encode(timeStamp+accessConfig.getSK()))){
            if(su!=null){
                resultMap.put("isSuccess",1);
                resultMap.put("desp","登录成功！");
                resultMap.put("data",su);
                session.setAttribute("sysUser",su);
                return om.writeValueAsString(resultMap);
            }else{
                resultMap.put("isSuccess",0);
                resultMap.put("desp","用户名或密码有误！");
                return om.writeValueAsString(resultMap);
            }
        }
        resultMap.put("isSuccess",0);
        resultMap.put("desp","签名错误！");
        return om.writeValueAsString(resultMap);
    }

    @RequestMapping("/loginOut")
    public String loginOut(@RequestParam String accessToken, @RequestParam String timeStamp, @RequestParam String data, HttpSession session) throws Exception{
        Map<String,Object> paramMap = JsonUtil.jsontoHashMap(data);
        Map<String,Object> resultMap = new HashMap<>();
        SysUser su = sysUserService.userLogin(paramMap);
        if (!accessToken.equals(ElectronicSignatureUtil.getSign(accessConfig.getAK(),timeStamp,accessConfig.getSK()))){
            resultMap.put("isSuccess",0);
            resultMap.put("desp","登出失败！");
            return om.writeValueAsString(resultMap);
        }else{
            session.removeAttribute("sysUser");
            resultMap.put("isSuccess",1);
            resultMap.put("desp","登出成功！");
            return om.writeValueAsString(resultMap);
        }
    }
}
