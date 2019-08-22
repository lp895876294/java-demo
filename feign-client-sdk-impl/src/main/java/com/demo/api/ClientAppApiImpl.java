package com.demo.api;

import com.demo.dto.ClientApp;
import com.fosung.framework.web.http.AppIBaseController;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ClientAppApiImpl extends AppIBaseController implements ClientAppApi {

    @Override
    @PostMapping(value = "/list")
    public List<ClientApp> queryApp(@RequestParam(name = "name") String name) {

        log.info("处理服务端接口: {}" , name);

        ClientApp clientApp = new ClientApp() ;

        clientApp.setId( getNextId() ) ;

        clientApp.setName( name ) ;

        return Lists.newArrayList( clientApp ) ;
    }
}
