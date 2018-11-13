package main.java.com.mydao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.mydao.model.AccessConfig;
import main.java.com.mydao.service.ViolationTypeService;
import main.java.com.mydao.utils.JsonUtil;
import main.java.com.mydao.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ViolationTypeController {

    private ObjectMapper om = new ObjectMapper();
    @Autowired
    private AccessConfig accessConfig;
    @Autowired
    private ViolationTypeService violationTypeService;
    @RequestMapping("/findTypeList")
    public String findTypeList(@RequestParam String param) throws Exception{
        //@RequestParam String accessToken, @RequestParam String timeStamp, @RequestParam String data
        Map<String,Object> parameterMap = JsonUtil.jsontoHashMap(param);
        String accessToken = parameterMap.get("accessToken").toString();
        String timeStamp = parameterMap.get("timeStamp").toString();
        Map<String,Object> paramMap1 = JsonUtil.jsontoHashMap(parameterMap.get("data").toString());
        Map<String,Object> resultMap = new HashMap<>();
        if (!accessToken.equals(accessConfig.getAK()+timeStamp+ Md5Util.MD5Encode(timeStamp+accessConfig.getSK()))){
            resultMap.put("isSuccess",0);
            resultMap.put("desp","用户不合法！");
            return om.writeValueAsString(resultMap);
        }
        resultMap.put("isSuccess",1);
        resultMap.put("desp","查询成功！");
        resultMap.put("data",violationTypeService.findList());
        return om.writeValueAsString(resultMap);
    }
}
