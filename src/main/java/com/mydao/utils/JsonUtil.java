package main.java.com.mydao.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;

/** 
* @Description: JSON 工具类
* @date 2016年1月11日 
* @author 1936
*/
public class JsonUtil {

private static ObjectMapper objectMapper  = new ObjectMapper();
    
    static{
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }  
    
    /**
     * 将对象转成json格式
     */
    public static String Object2Json(Object object){
        String jsonStr = "";
        if(null != object){
        	try {
                jsonStr = objectMapper.writeValueAsString(object);
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonStr;
    }
    
    /**
     * post请求添加参数（添加对象所有对象属性）
     */
    public static  void  Object2Json(PrintWriter printWriter, Object object){
    	if(null != object){
    		try {
                objectMapper.writeValue(printWriter,object);
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    	}
    }
    
     
    /**
     * 将json转成对象
     */
    public static Object JsonString2Object(String jsonStr, Class<?>  elementClasses){
        try {
            return objectMapper.readValue(jsonStr,elementClasses);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 从request中获取对应对象
     */
    public static Object JsonString2Object(HttpServletRequest request, Class<?>  elementClasses){
    	if(null != request){
    		try {
                return objectMapper.readValue(request.getReader(),elementClasses);
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    	}
        return null;
    }
    
    /**
     * 从返回流中中获取对应对象
     */
    public static Object JsonString2Object(BufferedReader bufferedReader, Class<?>  elementClasses){
    	if(null != bufferedReader){
    		try {
                return objectMapper.readValue(bufferedReader,elementClasses);
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    	}
        return null;
    }
    
    /**
     * JSON 转为 MAP 对象
     * @param object
     * @return
     */
	@SuppressWarnings({ "rawtypes" })
	public static HashMap<String, Object> jsontoHashMap(Object jsonObj) throws Exception {
		HashMap<String, Object> data = new HashMap<String, Object>();
		// 将json字符串转换成jsonObject
		JSONObject jsonObject = JSONObject.fromObject(jsonObj);
		Iterator  it = jsonObject.keys();
		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			Object value="";
			if(jsonObject.get(key)!=null){
				value = jsonObject.get(key);
			}
			data.put(key, value);
		}
		return data;
	}
    

}
