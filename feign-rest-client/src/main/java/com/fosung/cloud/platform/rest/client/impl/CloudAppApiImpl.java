package com.fosung.cloud.platform.rest.client.impl;

import com.fosung.cloud.platform.facade.application.entity.SysAppTerminal;
import com.fosung.cloud.platform.facade.application.entity.SysApplication;
import com.fosung.cloud.platform.facade.application.service.SysAppTerminalService;
import com.fosung.cloud.platform.facade.application.service.SysApplicationService;
import com.fosung.cloud.platform.facade.common.dict.AppTerminalType;
import com.fosung.cloud.platform.rest.client.api.CloudAppApi;
import com.fosung.cloud.platform.rest.client.dto.CloudApp;
import com.fosung.cloud.platform.rest.client.dto.CloudAppTerminal;
import com.fosung.framework.common.util.UtilBean;
import com.fosung.framework.common.util.UtilString;
import com.fosung.framework.web.http.AppIBaseController;
import com.google.common.base.Splitter;
import com.google.common.primitives.Longs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class CloudAppApiImpl extends AppIBaseController implements CloudAppApi {

    @Autowired
    private SysApplicationService sysApplicationService ;

    @Autowired
    private SysAppTerminalService sysAppTerminalService ;

    @Override
    public List<CloudApp> queryApp(@RequestParam(name = "dispatchChannel") String dispatchChannel ,
                                   @RequestParam(name = "terminalTypes") String terminalTypes){

        List<AppTerminalType> appTerminalTypes = null ;
        if(UtilString.isNotBlank( terminalTypes )){
            appTerminalTypes = Splitter.on(",").trimResults().omitEmptyStrings().splitToList( terminalTypes )
                    .stream().map( terminalType -> AppTerminalType.valueOf(terminalType) )
                    .collect( Collectors.toList() ) ;
        }

        // 应用id
        Set<Long> appIds = sysAppTerminalService.queryChannelAppIds( dispatchChannel , appTerminalTypes) ;

        List<SysApplication> applications = sysApplicationService.queryApplications( appIds ) ;

        return UtilBean.copyBeans( applications , CloudApp.class ) ;
    }

    @Override
    public List<CloudAppTerminal> queryAppTerminal(@RequestParam(name = "appIds") String appIds ,
                                                   @RequestParam(name = "dispatchChannel") String dispatchChannel ,
                                                   @RequestParam(name = "terminalType") String terminalType) {
        if( appIds==null || UtilString.isBlank( dispatchChannel ) || UtilString.isBlank( terminalType ) ){
            log.info("应用id，派发渠道，终端类型不能为空");
            return null ;
        }

        Set<Long> appIdSet = Splitter.on(",").omitEmptyStrings().trimResults().splitToList( appIds )
                .stream().map(appId -> Longs.tryParse(appId)).collect( Collectors.toSet() ) ;

        List<SysAppTerminal> sysAppTerminals = sysAppTerminalService.queryChannelAppTerminals(
                appIdSet , dispatchChannel , AppTerminalType.valueOf( terminalType ) ) ;

        // 返回应用列表
        return UtilBean.copyBeans( sysAppTerminals , CloudAppTerminal.class ) ;
    }

}
