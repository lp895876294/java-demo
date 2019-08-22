package com.demo.api;

import com.demo.dto.ClientApp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ClientAppApi" , url="${app.client.url}")
@RequestMapping("/api/client")
public interface ClientAppApi {

    /**
     * 查询应用列表信息
     * @param name 单个派发渠道
     * @return
     */
    @RequestMapping(value = "/list")
    List<ClientApp> queryApp(@RequestParam(name = "name") String name) ;

}
