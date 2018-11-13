package main.java.com.mydao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import main.java.com.mydao.entity.Violation;
import main.java.com.mydao.model.AccessConfig;
import main.java.com.mydao.model.FTPConfig;
import main.java.com.mydao.model.PageBean;
import main.java.com.mydao.service.ViolationService;
import main.java.com.mydao.utils.DateUtil;
import main.java.com.mydao.utils.HttpClientUtil;
import main.java.com.mydao.utils.JsonUtil;
import main.java.com.mydao.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ViolationController {


    @Autowired
    private ViolationService violationService;

    @Autowired
    private AccessConfig accessConfig;

    @Autowired
    private FTPConfig fTPConfig;

    private ObjectMapper om = new ObjectMapper();

    @RequestMapping("/findVioList")
    public String findList(@RequestParam String param) throws Exception{//@RequestParam String accessToken, @RequestParam String timeStamp, @RequestParam String data
        Map<String,Object> parameterMap = JsonUtil.jsontoHashMap(param);
        String accessToken = parameterMap.get("accessToken").toString();
        String timeStamp = parameterMap.get("timeStamp").toString();
        Map<String,Object> paramMap1 = JsonUtil.jsontoHashMap(parameterMap.get("data").toString());
        Map<String,Object> resultMap = new HashMap<>();
        if (!accessToken.equals(accessConfig.getAK()+timeStamp+ Md5Util.MD5Encode(timeStamp+accessConfig.getSK()))){
            resultMap.put("isSuccess",0);
            resultMap.put("desp","签名错误！");
            return om.writeValueAsString(resultMap);
        }
        PageHelper.startPage(Integer.parseInt(paramMap1.get("pageIndex").toString()), Integer.parseInt(paramMap1.get("pageSize").toString()));
        paramMap1.put("pageIndex",(Integer.parseInt(paramMap1.get("pageIndex").toString())-1)*Integer.parseInt(paramMap1.get("pageSize").toString()));
        paramMap1.put("pageSize",Integer.parseInt(paramMap1.get("pageSize").toString()));
        Integer resultCount = violationService.selectHisRecordsCount(paramMap1);
        List<Violation> resultList = violationService.selectHisRecords(paramMap1);
        PageInfo<Violation> pageData = new PageInfo<>(resultList);
        pageData.setTotal(resultCount);
        pageData.setList(resultList);
        resultMap.put("isSuccess",1);
        resultMap.put("desp","查询成功！");
        resultMap.put("data",pageData);
        return om.writeValueAsString(resultMap);
    }

    @RequestMapping("/findVioDetails")
    public String findDetail(@RequestParam String param) throws  Exception{
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
        resultMap.put("data",violationService.selectByPrimaryKey(Long.parseLong(paramMap1.get("recId").toString())));
        return om.writeValueAsString(resultMap);
    }

    @RequestMapping("/insertVio")
    public String insertVio(@RequestParam String param) throws Exception{//@RequestParam String accessToken, @RequestParam String timeStamp, @RequestParam String data
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
        Violation violation = (Violation)JsonUtil.JsonString2Object(paramMap1.get("data").toString(),Violation.class);
        //不包含faceId
        if (violation.getFacialFeature().indexOf("http://") != -1){
            //添加人脸
            String url = "https://222.180.239.210:4436/ISAPI/Intelligent/FDLib/FaceDataRecord?format=json";
            String jsonStr = "{\"faceURL\":\""+violation.getFacialFeature()+"\",\"faceLibType\":\"blackFD\",\"FDID\":\"1\",\"name\":\""+new String(violation.getPsersonName().getBytes(), "utf-8")+"\",\"bornTime\":\"2000-01-01\"}";
            String httpOrgCreateTestRtn = HttpClientUtil.sendHttpsReq(url, jsonStr, "utf-8","post");
            Map<String,Object> resMap = JsonUtil.jsontoHashMap(httpOrgCreateTestRtn);
            //添加成功  返回faceID
            if (resMap.get("FPID").toString()!="" && resMap.get("FPID").toString()!= null){
                resultMap.put("faceId",resMap.get("FPID").toString());
                violation.setFacialFeature(resMap.get("FPID").toString());
            }else{
                //添加失败 Return
                resultMap.put("isSuccess",0);
                resultMap.put("desp","添加人脸失败！");
                return  om.writeValueAsString(resultMap);
            }
        }

        //保存违章数据
        if (violationService.insertSelective(violation)>0){
            resultMap.put("isSuccess",1);
            resultMap.put("desp","保存成功！");
            return om.writeValueAsString(resultMap);
        }
        resultMap.put("isSuccess",0);
        resultMap.put("desp","保存失败！");
        return om.writeValueAsString(resultMap);
    }

    @RequestMapping("/findListByUser")
    public String findListByUser(@RequestParam String param) throws Exception{
        Map<String,Object> parameterMap = JsonUtil.jsontoHashMap(param);
        String accessToken = parameterMap.get("accessToken").toString();
        String timeStamp = parameterMap.get("timeStamp").toString();
        Map<String,Object> paramMap1 = JsonUtil.jsontoHashMap(parameterMap.get("data").toString());
        Map<String,Object> resultMap = new HashMap<>();
        if (!accessToken.equals(accessConfig.getAK()+timeStamp+ Md5Util.MD5Encode(timeStamp+accessConfig.getSK()))){
            resultMap.put("isSuccess",0);
            resultMap.put("desp","签名错误！");
            return om.writeValueAsString(resultMap);
        }
        paramMap1.put("idate",DateUtil.getAfterDateByDays(new Date(),paramMap1.get("idate").toString().equals("1")?0:paramMap1.get("idate").toString().equals("2")?-2:-6));
        paramMap1.put("pageIndex",(Integer.parseInt(paramMap1.get("pageIndex").toString())-1)*Integer.parseInt(paramMap1.get("pageSize").toString()));
        paramMap1.put("pageSize",Integer.parseInt(paramMap1.get("pageSize").toString()));
        List<Violation> resultList = violationService.selectHisRecordsByUser(paramMap1);
        Integer resultCount = violationService.selectHisRecordsCountByUser(paramMap1);
        PageInfo<Violation> pageDate = new PageInfo<>(resultList);
        pageDate.setTotal(resultCount);
        pageDate.setList(resultList);
        resultMap.put("isSuccess",1);
        resultMap.put("desp","查询成功！");
        resultMap.put("data",pageDate);
        return om.writeValueAsString(resultMap);
    }
}
