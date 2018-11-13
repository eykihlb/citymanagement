package main.java.com.mydao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.mydao.model.AccessConfig;
import main.java.com.mydao.model.FTPConfig;
import main.java.com.mydao.model.PicUrlModel;
import main.java.com.mydao.utils.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FaceComparisonController {

    private final static Logger logger = LoggerFactory.getLogger(FaceComparisonController.class);

    private ObjectMapper om = new ObjectMapper();

    @Autowired
    private AccessConfig accessConfig;

    @Autowired
    private FTPConfig fTPConfig;
    @RequestMapping("/comparison")
    public String faceComparison(@RequestParam(value = "file") MultipartFile[] multipartFile,@RequestParam String param) throws Exception{
        Map<String,Object> parameterMap = JsonUtil.jsontoHashMap(param);
        String accessToken = parameterMap.get("accessToken").toString();
        String timeStamp = parameterMap.get("timeStamp").toString();
        PicUrlModel pum = new PicUrlModel();
        Map<String,Object> resultMap = new HashMap<>();
        if (!accessToken.equals(accessConfig.getAK()+timeStamp+ Md5Util.MD5Encode(timeStamp+accessConfig.getSK()))){
            resultMap.put("isSuccess",0);
            resultMap.put("desp","用户不合法！");
            return om.writeValueAsString(resultMap);
        }
        if (multipartFile[0].getSize()>1000000||multipartFile[1].getSize()>1000000){
            resultMap.put("isSuccess",0);
            resultMap.put("desp","图片太大！");
            return om.writeValueAsString(resultMap);
        }
        if (multipartFile != null) {
            //多文件上传
            for (int i = 0;i<multipartFile.length;i++){
                if (StringUtils.hasText(multipartFile[i].getOriginalFilename())) {
                    String fileName = multipartFile[i].getOriginalFilename();
                    InputStream inputStream = multipartFile[i].getInputStream();
                    String  imgUrl = FTPUtil.uploadFile(fTPConfig,fileName,inputStream);
                    if (!"0".equals(imgUrl)){
                        if (i == 0) {
                            pum.setFaceImg(imgUrl);
                        }else{
                            pum.setFullImg(imgUrl);
                        }
                    }else{
                        resultMap.put("isSuccess",0);
                        resultMap.put("desp","图片上传失败！");
                        return om.writeValueAsString(resultMap);
                    }
                }
            }

        }

        //人脸库以图搜图
        String url = "https://222.180.239.210:4436/ISAPI/Intelligent/FDLib/searchByPic?format=json";
        String jsonStr = "{\"searchResultPosition\":0,\"maxResults\":100,\"modelMaxNum\":100,\"taskID\":\"\",\"FDLib\":[{\"FDID\":\"1\"}],\"dataType\":\"URL\",\"faceURL\":\""+pum.getFaceImg()+"\",\"maxSimilarity\":0.99,\"minSimilarity\":0.00}";
        String httpOrgCreateTestRtn = HttpClientUtil.sendHttpsReq(url, jsonStr, "utf-8","post");
        Map<String,Object> map = JsonUtil.jsontoHashMap(httpOrgCreateTestRtn);
        if ("1".equals(map.get("statusCode"))){
            Double similarity = 0D;
            JSONArray jan = (JSONArray)map.get("MatchList");
            if(jan!=null||jan.size()!=0){
                for(int i=0;i<jan.size();i++){
                    JSONObject jo = JSONObject.fromObject(jan.get(i));
                    if (Double.parseDouble(jo.get("similarity").toString())>similarity){
                        pum.setFaceId(jo.getString("FPID"));
                        similarity = Double.parseDouble(jo.get("similarity").toString());
                    }
                }
            }
        }

        resultMap.put("data",pum);
        resultMap.put("isSuccess",1);
        resultMap.put("desp","执行成功！");
        return om.writeValueAsString(resultMap);
    }
}
