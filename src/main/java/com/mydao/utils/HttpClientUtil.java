package main.java.com.mydao.utils;

import groovy.util.IFileNameFinder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import sun.nio.cs.ext.GBK;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    /**
     * get请求，参数拼接在地址上
     * @param url 请求地址加参数
     * @return 响应
     */
    public String httpGet(String url){
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(get);
            if(response != null && response.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity entity = response.getEntity();
                result = entityToString(entity);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeAll(httpClient,response);
        }
        return null;
    }

    /**
     * get请求，参数放在map里
     * @param url 请求地址
     * @param map 参数map
     * @return 响应
     */
    public String getMap(String url,Map<String,String> map)
    {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> pairs = new ArrayList<>();
        for(Map.Entry<String,String> entry : map.entrySet())
        {
            pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
        }
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            builder.setParameters(pairs);
            HttpGet get = new HttpGet(builder.build());
            response = httpClient.execute(get);
            if(response != null && response.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity entity = response.getEntity();
                result = entityToString(entity);
            }
            return result;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeAll(httpClient,response);
        }

        return null;
    }

    /**
     * 发送post请求，参数用map接收
     * @param url 地址
     * @param map 参数
     * @return 返回值
     */
    public String postMap(String url,Map<String,String> map) {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for(Map.Entry<String,String> entry : map.entrySet())
        {
            pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
        }
        CloseableHttpResponse response = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));
            response = httpClient.execute(post);
            if(response != null && response.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity entity = response.getEntity();
                result = entityToString(entity);
            }
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeAll(httpClient,response);

        }
        return null;
    }

    /**
     * post请求，参数为json字符串
     * @param url 请求地址
     * @param jsonString json字符串
     * @return 响应
     */
    public String postJson(String url,String jsonString)
    {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            post.setEntity(new ByteArrayEntity(jsonString.getBytes("UTF-8")));
            response = httpClient.execute(post);
            if(response != null && response.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity entity = response.getEntity();
                result = entityToString(entity);
            }
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeAll(httpClient,response);
        }
        return null;
    }

    private String entityToString(HttpEntity entity) throws IOException {
        String result = null;
        if(entity != null)
        {
            long lenth = entity.getContentLength();
            if(lenth != -1 && lenth < 2048)
            {
                result = EntityUtils.toString(entity,"UTF-8");
            }else {
                InputStreamReader reader1 = new InputStreamReader(entity.getContent(), "UTF-8");
                CharArrayBuffer buffer = new CharArrayBuffer(2048);
                char[] tmp = new char[1024];
                int l;
                while((l = reader1.read(tmp)) != -1) {
                    buffer.append(tmp, 0, l);
                }
                result = buffer.toString();
            }
        }
        return result;
    }

    private void  closeAll(CloseableHttpClient httpClient,CloseableHttpResponse response){
        try {
            httpClient.close();
            if(response != null)
            {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("resource")
    public static String sendHttpsReq(String url,String jsonstr,String charset,String reqType){
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpGet httpGet = null;
        String result = null;
        try{
            httpClient = new SSLClient();

            httpPost = new HttpPost(url);

            httpGet = new HttpGet(url);

            httpGet.addHeader("Content-Type", "application/json");
            httpPost.addHeader("Content-Type", "application/json");

            String encoding = DatatypeConverter.printBase64Binary("admin:Hik12345".getBytes("UTF-8"));

            httpPost.setHeader("Authorization", "Basic " +encoding);
            httpGet.setHeader("Authorization", "Basic " +encoding);

            StringEntity se = new StringEntity(jsonstr,"utf-8");
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httpPost.setEntity(se);
            HttpResponse response = null;
            if ("post".equals(reqType)){
                response = httpClient.execute(httpPost);
            }else{
                response = httpClient.execute(httpGet);
            }

            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    public static void main(String[] args) throws Exception{
        //人脸库以图搜图
        //String url = "https://222.180.239.210:4436/ISAPI/Intelligent/FDLib/searchByPic?format=json";
        //String jsonStr = "{\"searchResultPosition\":0,\"maxResults\":100,\"modelMaxNum\":100,\"taskID\":\"\",\"FDLib\":[{\"FDID\":\"1\"}],\"dataType\":\"URL\",\"faceURL\":\""+"http://47.94.57.213/12345.jpg"+"\",\"maxSimilarity\":0.99,\"minSimilarity\":0.00}";
        //添加人脸
        //Object obj = JsonUtil.JsonString2Object(jsonStr,Object.class);
        String url = "https://222.180.239.210:4436/ISAPI/Intelligent/FDLib/FaceDataRecord?format=json";
        String jsonStr = "{\"faceURL\":\"http://47.94.57.213:8080/mavenWeb/images/12345.jpg\",\"faceLibType\":\"blackFD\",\"FDID\":\"1\",\"name\":\""+new String("对对对".getBytes(), "utf-8")+"\",\"gender\":\"male\",\"bornTime\":\"2004-05-03\",\"city\":\"130100\",\"certificateType \":\"ID\",\"certificateNumber\":\"610424188809092929\",\"caseInfo\":\"weizhangtingche\",\"tag\":\"aa,bb,cc,dd\",\"address\":\"beijingerhuan\",\"customInfo\":\"testData\"}";
        //String jsonStr = "{\"faceLibType\":\"blackFD\",\"FDID\":\"1\",\"name\":\"zhangsan\",\"gender\":\"male\",\"bornTime\":\"2004-05-03\",\"city\":\"130100\",\"certificateType \":\"ID\",\"certificateNumber\":\"610424188809092929\",\"caseInfo\":\"weizhangtingche\",\"tag\":\"aa,bb,cc,dd\",\"address\":\"beijingerhuan\",\"customInfo\":\"testData\"}---------------------------7e13971310878"+Base64Utils.ImageToBase64ByLocal("C:/Users/Administrator.USER01801171630/Desktop/12345.jpg");
        System.out.println(jsonStr);
        String httpOrgCreateTestRtn = HttpClientUtil.sendHttpsReq(url, jsonStr, "utf-8","post");
        Map<String,Object> map = JsonUtil.jsontoHashMap(httpOrgCreateTestRtn);

        System.out.println(httpOrgCreateTestRtn);
    }
}
