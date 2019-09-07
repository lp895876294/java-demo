package com.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.api.ClientSourceAppApi;
import com.google.common.collect.Maps;
import feign.Feign;
import feign.codec.StringDecoder;
import feign.form.ContentType;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

import java.util.Map;

public class ClientSourceApplication {

    @Test
    public void queryApp() {
        ClientSourceAppApi clientSourceAppApi = Feign.builder()
                .encoder(new FormEncoder())
                .decoder(new JacksonDecoder())
                .target(ClientSourceAppApi.class, "http://localhost:8081");

        Object result = clientSourceAppApi.queryApp("测试");

        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void executeGetRequest() {
        ClientSourceAppApi clientSourceAppApi = Feign.builder()
                .encoder(new FormEncoder())
                .decoder(new StringDecoder())
                .target(ClientSourceAppApi.class, "http://localhost:8081");

        Map<String,Object> queryParam = Maps.newHashMap() ;
        queryParam.put("name", "测试-GET") ;

        Map<String,String> headerMap = Maps.newHashMap() ;
        headerMap.put("Content-Type", "application/x-www-form-urlencoded") ;

        String result = clientSourceAppApi.executeGetRequest("/api/client/list" , queryParam , headerMap );

        result = StringEscapeUtils.unescapeJson( result ) ;

        System.out.println( result );

        JSONArray jsonObject = JSON.parseObject( result , JSONArray.class ) ;

        System.out.println(JSON.toJSONString(jsonObject));
    }

    @Test
    public void executePostRequest() {
        ClientSourceAppApi clientSourceAppApi = Feign.builder()
                .encoder(new FormEncoder())
                .decoder(new StringDecoder())
                .target(ClientSourceAppApi.class, "http://localhost:8081");

        Map<String,Object> queryParam = Maps.newHashMap() ;
        queryParam.put("name", "测试-POST") ;

        Map<String,String> headerMap = Maps.newHashMap() ;
        headerMap.put("Content-Type", "application/x-www-form-urlencoded") ;

        String result = clientSourceAppApi.executePostRequest("/api/client/list" , queryParam , headerMap );

        result = StringEscapeUtils.unescapeJson( result ) ;

        System.out.println( result );
    }

}
