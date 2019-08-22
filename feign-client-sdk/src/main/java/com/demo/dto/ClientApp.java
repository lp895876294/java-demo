package com.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClientApp implements Serializable {

    private static final long serialVersionUID = 1L ;

    // 应用终端id
    private Long id ;

    // 终端名称
    private String name ;
}
