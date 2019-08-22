package com.demo.api;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface ClientSourceAppApi {

    @RequestLine("GET /api/client/list")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Object queryApp(@Param("name") String name);

}
