package com.demo.api;

import feign.*;

import java.net.URI;
import java.util.Map;

public interface ClientSourceAppApi {

    @RequestLine("GET /api/client/list")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Object queryApp(@Param("name") String name);

    @RequestLine("GET {uri}")
    String executeGetRequest(URI host , @Param("uri") String uri , @QueryMap Map<String,Object> queryParam , @HeaderMap Map<String,String> headerMap) ;

    @RequestLine("POST {uri}")
    String executePostRequest(URI host , @Param("uri") String uri , @QueryMap Map<String,Object> queryParam , @HeaderMap Map<String,String> headerMap) ;


}
