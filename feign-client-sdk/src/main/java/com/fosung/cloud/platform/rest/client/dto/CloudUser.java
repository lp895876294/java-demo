package com.fosung.cloud.platform.rest.client.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CloudUser implements Serializable {

    private static final long serialVersionUID = 1L ;

    private Long id ;

    private String realName ;

    private String telephone ;

    private String sex ;

    private String identifyId ;

    private String source ;
}
