package com.demo.api;

import com.demo.dto.ClientApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ClientRest {

    @Autowired
    private ClientAppApi clientAppApi ;

    @RequestMapping(value = "/query")
    public List<ClientApp> queryApp(@RequestParam(required = false , defaultValue = "默认名称") String name) {

        log.info("客户端调用,name={}" , name);

        List<ClientApp> clientApps = clientAppApi.queryApp( name ) ;

        return clientApps ;
    }
}
