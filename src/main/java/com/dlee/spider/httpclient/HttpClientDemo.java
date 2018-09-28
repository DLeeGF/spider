package com.dlee.spider.httpclient;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("httpclient")
public class HttpClientDemo {
    @Value("${url}")
    private String url;

    @GetMapping("/init")
    public void initClient(){
        CloseableHttpClient client= HttpClients.createDefault();
        HttpGet httpGet=new HttpGet(url);
        HttpPost httpPost=new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("domain", "baidu.com"));
        nvps.add(new BasicNameValuePair("isplogin", "true"));
        nvps.add(new BasicNameValuePair("submit", "登录"));
        nvps.add(new BasicNameValuePair("email", ""));
        nvps.add(new BasicNameValuePair("passwd", ""));

        try{
            httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
            CloseableHttpResponse httpResponse = client.execute(httpPost);

            //如果模拟登录成功
            if(httpResponse.getStatusLine().getStatusCode() == 200) {
                Header[] headers = httpResponse.getAllHeaders();
                for (Header header : headers) {
                    System.out.println(header.getName() + ": " + header.getValue());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
//        httpGet.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//        httpGet.setHeader("Accept-Encoding","gzip, deflate, br");
//        httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.9");
//        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
//        try
//        {
//            CloseableHttpResponse httpResponse = client.execute(httpGet);
//            if (httpResponse.getStatusLine().getStatusCode() == 200) {
//                //打印所有响应头
//                Header[] headers = httpResponse.getAllHeaders();
//                for (Header header : headers) {
//                    System.out.println(header.getName() + ": " + header.getValue());
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
