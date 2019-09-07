package com.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.api.ClientSourceAppApi;
import com.google.common.collect.Maps;
import feign.Feign;
import feign.Target;
import feign.codec.StringDecoder;
import feign.form.ContentType;
import feign.form.FormEncoder;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.ribbon.LBClient;
import feign.ribbon.LBClientFactory;
import feign.ribbon.RibbonClient;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

import java.net.URI;
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
                .target( Target.EmptyTarget.create( ClientSourceAppApi.class ) ) ;

        Map<String,Object> queryParam = Maps.newHashMap() ;
        queryParam.put("name", "测试-GET") ;

        Map<String,String> headerMap = Maps.newHashMap() ;
        headerMap.put("Content-Type", "application/x-www-form-urlencoded") ;

        String result = clientSourceAppApi.executeGetRequest( URI.create("http://localhost:8081/") ,"/api/client/list" , queryParam , headerMap );

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
                .target( Target.EmptyTarget.create( ClientSourceAppApi.class ) );

        Map<String,Object> queryParam = Maps.newHashMap() ;
        queryParam.put("name", "测试-POST") ;

        Map<String,String> headerMap = Maps.newHashMap() ;
        headerMap.put("Content-Type", "application/x-www-form-urlencoded") ;

        String result = clientSourceAppApi.executePostRequest( URI.create("http://localhost:8081/") ,"/api/client/list" , queryParam , headerMap );

        result = StringEscapeUtils.unescapeJson( result ) ;

        System.out.println( result );
    }

    @Test
    public void executeRibbon() {

//        RibbonClient.builder().lbClientFactory(new LBClientFactory() {
//            @Override
//            public LBClient create(String clientName) {
//                return null;
//            }
//        })

        ClientSourceAppApi clientSourceAppApi = Feign.builder()
//                .client(RibbonClient.create())
                .encoder(new FormEncoder())
                .decoder(new StringDecoder())
                .target( ClientSourceAppApi.class , "http://localhost:8081/" );

        Map<String,Object> queryParam = Maps.newHashMap() ;
        queryParam.put("name", "测试-POST") ;

        Map<String,String> headerMap = Maps.newHashMap() ;
        headerMap.put("Content-Type", "application/x-www-form-urlencoded") ;

        String result = clientSourceAppApi.executePostRequest( URI.create("http://localhost:8081/") ,"/api/client/list" , queryParam , headerMap );

        result = StringEscapeUtils.unescapeJson( result ) ;

        System.out.println( result );
    }

    @Test
    public void executeHystrix() {

        ClientSourceAppApi clientSourceAppApi = HystrixFeign.builder()
                .encoder(new FormEncoder())
                .decoder(new StringDecoder())
                .target( ClientSourceAppApi.class , "http://localhost:8081/" );

        Map<String,Object> queryParam = Maps.newHashMap() ;
        queryParam.put("name", "测试-POST") ;

        Map<String,String> headerMap = Maps.newHashMap() ;
        headerMap.put("Content-Type", "application/x-www-form-urlencoded") ;

        String result = clientSourceAppApi.executePostRequest( URI.create("http://localhost:8081/") ,"/api/client/list" , queryParam , headerMap );

        result = StringEscapeUtils.unescapeJson( result ) ;

        System.out.println( result );
    }

}
