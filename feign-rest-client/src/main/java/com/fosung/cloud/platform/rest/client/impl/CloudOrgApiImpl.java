package com.fosung.cloud.platform.rest.client.impl;


import com.fosung.cloud.platform.facade.user.entity.SysOrg;
import com.fosung.cloud.platform.facade.user.service.SysOrgService;
import com.fosung.cloud.platform.rest.client.api.CloudOrgApi;
import com.fosung.cloud.platform.rest.client.dto.CloudOrg;
import com.fosung.framework.common.json.JsonMapper;
import com.fosung.framework.common.util.UtilBean;
import com.fosung.framework.common.util.UtilString;
import com.fosung.framework.web.http.AppIBaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CloudOrgApiImpl extends AppIBaseController implements CloudOrgApi {

    @Autowired
    private SysOrgService sysOrgService ;

    @Override
    public List<CloudOrg> queryRootOrgs(@RequestParam(name = "source")String source) {

        List<SysOrg> sysOrgs = sysOrgService.queryRootOrgs( source ) ;

        log.info("查询根组织信息:{}" , JsonMapper.toJSONString( sysOrgs ));

        return UtilBean.copyBeans( sysOrgs , CloudOrg.class ) ;
    }

    @Override
    public List<CloudOrg> querySubOrgs(@RequestParam(name = "source") String source ,
                                       @RequestParam(name = "parent_id") Long parentId) {
        List<SysOrg> sysOrgs = sysOrgService.querySubOrgs( source , parentId ) ;

        log.info("查询子组织信息:{}" , JsonMapper.toJSONString( sysOrgs ));

        return UtilBean.copyBeans( sysOrgs , CloudOrg.class ) ;
    }

    @Override
    public CloudOrg queryOrgInfo(@RequestParam(name = "org_id") Long orgId) {
        //查询组织机构信息
        SysOrg sysOrg = sysOrgService.get( orgId ) ;

        return UtilBean.copyBean( sysOrg , CloudOrg.class ) ;
    }

    @Override
    @PostMapping(value = "/outId/info")
    public CloudOrg queryOutIdOrgInfo(@RequestParam(name = "org_out_id") String orgOutId) {
        if(UtilString.isBlank( orgOutId )){
            return null;
        }

        SysOrg sysOrg = sysOrgService.queryOrgByOutId( orgOutId ) ;

        return UtilBean.copyBean( sysOrg , CloudOrg.class ) ;
    }
}
