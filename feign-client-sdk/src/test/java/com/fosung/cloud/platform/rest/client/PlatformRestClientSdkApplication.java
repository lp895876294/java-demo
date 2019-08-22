package com.fosung.cloud.platform.rest.client;

import feign.Feign;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class PlatformRestClientSdkApplication {

    public static void main(String[] args) throws IOException {
        feignInvoke() ;
    }

    public static void feignInvoke() {

        Feign.Builder feignBuilder = Feign.builder() ;

//        AppCloudClientApplicationApi clientApplicationApi = feignBuilder
//                .contract( new SpringMvcContract() )
//                .options( new Request.Options( 3000 , 5000 ) )
//                .decoder( new JacksonDecoder() )
//                .logger( new Slf4jLogger() )
//                .logLevel( Logger.Level.FULL )
//                .target(AppCloudClientApplicationApi.class , "http://10.254.23.69:9093/" ) ;
//
////        List<ClientApp> clientAppList = clientApplicationApi.queryValidApps() ;
////
////        log.info( JSON.toJSONString( clientAppList ) ) ;
//
//        List<ClientAppNode> clientAppNodeList = clientApplicationApi.queryValidAppNodes("1") ;
//
//        log.info( JSON.toJSONString( clientAppNodeList ) ) ;
    }

}
