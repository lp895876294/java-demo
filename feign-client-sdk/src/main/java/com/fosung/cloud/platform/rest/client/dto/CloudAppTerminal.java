package com.fosung.cloud.platform.rest.client.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CloudAppTerminal implements Serializable {

    private static final long serialVersionUID = 1L ;

    // 应用终端id
    private Long id ;

    // 应用终端所属应用id
    private Long appId ;

    // 终端名称
    private String name ;

    // 终端编码
    private String code ;

    // 终端图标
    private String icon ;

    // 终端url
    private String url ;
}
