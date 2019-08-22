package com.fosung.cloud.platform.rest.client.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CloudUserDto implements Serializable {

    private static final long serialVersionUID = 1L ;
	
    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 真实姓名
     */
    private String realName;

}