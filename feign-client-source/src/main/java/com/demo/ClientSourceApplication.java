package com.demo;

import com.alibaba.fastjson.JSON;
import com.demo.api.ClientSourceAppApi;
import feign.Feign;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;

public class ClientSourceApplication {

    public static void main(String[] args) {

        ClientSourceAppApi clientSourceAppApi = Feign.builder()
                .encoder(new FormEncoder())
                .decoder( new JacksonDecoder())
                .target(ClientSourceAppApi.class , "http://localhost:8081") ;

        Object result = clientSourceAppApi.queryApp( "测试" ) ;

        System.out.println( JSON.toJSONString( result ) );

    }

}
