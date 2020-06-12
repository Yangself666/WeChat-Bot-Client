package cn.yangself.wechatBotClient.utils.NetPostRequest;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这个类用来发送Post请求
 */
@Component
public class NetRequest {

    @Autowired
    private RestTemplate restTemplate ;

    /**
     * 发送post请求
     * @param paramsMap
     * @param url
     * @return
     */
    public Map sendPost(String url, Map paramsMap){
        Map res = new HashMap();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
            //用HttpEntity封装整个请求报文
            HttpEntity<String> httpEntity= new HttpEntity(paramsMap,headers);

            String backInfo = getRestTemplateBuilder().postForObject(url, httpEntity, String.class);
            res = JSON.parseObject(backInfo);
        } catch (Exception e) {
            res.put("code", 500);
            res.put("success", false);
            res.put("messgae", "参数有误");
            e.printStackTrace();
        }
        return res;
    }

    public RestTemplate getRestTemplateBuilder(){
        List<HttpMessageConverter<?>> httpMessageConverters = restTemplate.getMessageConverters();
        httpMessageConverters.stream().forEach(httpMessageConverter -> {
            if (httpMessageConverter instanceof StringHttpMessageConverter) {
                StringHttpMessageConverter messageConverter = (StringHttpMessageConverter) httpMessageConverter;
                messageConverter.setDefaultCharset(Charset.forName("UTF-8"));
            }
        });
        return restTemplate;
    }

}
