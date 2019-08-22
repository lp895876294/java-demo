package com.fosung.cloud.platform.rest.client.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CloudOrg implements Serializable {

    private static final long serialVersionUID = 1L ;

    private Long id ;

    private String name ;

    private String code ;

    private String source ;

    private String outId ;
    /**
     * 组织名称
     */
    private Long parentId ;
}
