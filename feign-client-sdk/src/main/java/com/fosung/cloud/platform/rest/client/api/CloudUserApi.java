package com.fosung.cloud.platform.rest.client.api;

import com.fosung.cloud.platform.rest.client.dto.CloudOrg;
import com.fosung.cloud.platform.rest.client.dto.CloudUser;
import com.fosung.cloud.platform.rest.client.dto.CloudUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "CloudUserApi" , url="${app.cloud.client.url}")
@RequestMapping("/api/cloud/user")
public interface CloudUserApi {

    /***
     * 查询用户信息
     * userName realName   telephone  id  任意一个即可
     * @param
     * @return
     */
    @PostMapping(value = "/query")
    List<CloudUser> queryUser(@RequestBody CloudUserDto sysUser) ;

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    @PostMapping(value = "/get")
    CloudUser queryByUserId(@RequestParam(name = "userid") Long userId) ;

    /**
     * 根据外部用户id查询用户信息
     * @param outId
     * @return
     */
    @PostMapping(value = "/get/byoutid")
    CloudUser queryByOutUserId(@RequestParam(name = "outId") String outId) ;

    /**
     * 根据身份证号查询用户
     * @param identifyId
     * @return
     */
    @PostMapping(value = "/get/identifyId")
    CloudUser queryByIdentifyId(@RequestParam(name = "identifyId")String identifyId) ;

    /**
     * 查询组织机构下的用户
     * @param source
     * @param orgId
     * @return
     */
    @PostMapping(value = "/list")
    List<CloudUser> queryOrgUsers(@RequestParam(name = "source") String source ,
                                  @RequestParam(name = "orgid") Long orgId) ;

    /**
     * 根据组织机构编码查询组织机构下的用户
     * @param source
     * @param orgCode
     * @return
     */
    @PostMapping(value = "/list/byorgcode")
    List<CloudUser> queryOrgUsersByCode(@RequestParam(name = "source") String source ,
                                  @RequestParam(name = "orgCode") String orgCode) ;

    /**
     * 查询用户所属的组织机构
     * @param source
     * @param userId
     * @return
     */
    @PostMapping(value = "/org")
    CloudOrg queryUserOrg(@RequestParam(name = "source") String source ,
                          @RequestParam(name = "userid") Long userId) ;

}