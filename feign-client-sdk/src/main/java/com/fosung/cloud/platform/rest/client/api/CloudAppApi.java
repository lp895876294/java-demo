package com.fosung.cloud.platform.rest.client.api;

import com.fosung.cloud.platform.rest.client.dto.CloudApp;
import com.fosung.cloud.platform.rest.client.dto.CloudAppTerminal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "CloudAppApi" , url="${app.cloud.client.url}")
@RequestMapping("/api/cloud/app")
public interface CloudAppApi {

    /**
     * 查询应用列表信息
     * @param dispatchChannel 单个派发渠道
     * @param terminalTypes 终端类型，不同的终端类型使用逗号分隔
     * @return
     */
    @PostMapping(value = "/list")
    List<CloudApp> queryApp(@RequestParam(name = "dispatchChannel") String dispatchChannel ,
                            @RequestParam(name = "terminalTypes") String terminalTypes) ;

    /**
     * 查询应用终端列表
     * @param appIds 应用id，不同的id使用逗号分隔
     * @param dispatchChannel 单个派发渠道
     * @param terminalType
     * @return
     */
    @PostMapping(value = "/terminal/list")
    List<CloudAppTerminal> queryAppTerminal(@RequestParam(name = "appIds") String appIds ,
                                            @RequestParam(name = "dispatchChannel") String dispatchChannel ,
                                            @RequestParam(name = "terminalType") String terminalType) ;

}
