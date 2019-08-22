package com.fosung.cloud.platform.rest.client.api;

import com.fosung.cloud.platform.rest.client.dto.CloudOrg;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "CloudOrgApi" , url="${app.cloud.client.url}")
@RequestMapping("/api/cloud/org")
public interface CloudOrgApi {

    /**
     * 查询根组织机构
     * @param source
     * @return
     */
    @PostMapping(value = "/root")
    List<CloudOrg> queryRootOrgs(@RequestParam(name = "source") String source) ;

    /**
     * 查询直接子节点
     * @param source
     * @param parentId
     * @return
     */
    @PostMapping(value = "/suborg")
    List<CloudOrg> querySubOrgs(@RequestParam(name = "source") String source ,
                                @RequestParam(name = "parent_id") Long parentId) ;

    /**
     * 查询组织机构信息
     * @param orgId
     * @return
     */
    @PostMapping(value = "/info")
    CloudOrg queryOrgInfo(@RequestParam(name = "org_id") Long orgId) ;

    /**
     * 查询组织机构信息
     * @param orgOutId
     * @return
     */
    @PostMapping(value = "/outId/info")
    CloudOrg queryOutIdOrgInfo(@RequestParam(name = "org_out_id") String orgOutId) ;

}