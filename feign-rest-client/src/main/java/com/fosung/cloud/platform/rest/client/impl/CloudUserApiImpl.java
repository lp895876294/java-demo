package com.fosung.cloud.platform.rest.client.impl;

import com.fosung.cloud.platform.facade.impl.user.service.SysUserServiceImpl;
import com.fosung.cloud.platform.facade.user.entity.SysOrg;
import com.fosung.cloud.platform.facade.user.entity.SysUser;
import com.fosung.cloud.platform.facade.user.service.SysOrgService;
import com.fosung.cloud.platform.rest.client.api.CloudUserApi;
import com.fosung.cloud.platform.rest.client.dto.CloudOrg;
import com.fosung.cloud.platform.rest.client.dto.CloudUser;
import com.fosung.cloud.platform.rest.client.dto.CloudUserDto;
import com.fosung.framework.common.util.UtilBean;
import com.fosung.framework.common.util.UtilString;
import com.fosung.framework.web.http.AppIBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CloudUserApiImpl extends AppIBaseController implements CloudUserApi {

    @Autowired
    private SysUserServiceImpl sysUserService ;

    @Autowired
    private SysOrgService sysOrgService ;

    /**
     * 根据条件查询 用户
     */
    @Override
    public List<CloudUser> queryUser(@RequestBody CloudUserDto cloudUser) {
        //获取查询参数
        //Map<String, Object> searchParam = getParametersStartingWith(getHttpServletRequest(),"");
        Map<String, Object> searchParam = new HashMap<String, Object>();
		/*if(searchParam==null||searchParam.size()==0) {
			return reList;
		}*/
        if(UtilString.isNotEmpty( cloudUser.getUserName())) {
            searchParam.put("userName", cloudUser.getUserName());
        }
        if(UtilString.isNotEmpty( cloudUser.getRealName())) {
            searchParam.put("realname", cloudUser.getRealName());
        }
        if(UtilString.isNotEmpty( cloudUser.getTelephone())) {
            searchParam.put("telephone", cloudUser.getTelephone());
        }
        if(searchParam.size()==0) {
            return null;
        }

        List<SysUser> uList = sysUserService.queryAll(searchParam );

        return UtilBean.copyBeans(uList, CloudUser.class);
    }

    @Override
    public CloudUser queryByUserId(@RequestParam(name = "userid")Long userId) {
        if( userId==null ){
            return null ;
        }

        SysUser sysUser = sysUserService.get( userId ) ;

        return UtilBean.copyBean(sysUser, CloudUser.class);
    }

    @PostMapping(value = "/get/byoutid")
    @Override
    public CloudUser queryByOutUserId(@RequestParam(name = "outId") String outId){
        if( UtilString.isBlank( outId ) ){
            return null ;
        }
        SysUser sysUser = sysUserService.getUserByOutId( outId ) ;

        return UtilBean.copyBean(sysUser, CloudUser.class);
    }

    @Override
    @PostMapping(value = "/get/identifyId")
    public CloudUser queryByIdentifyId(@RequestParam(name = "identifyId")String identifyId){
        SysUser sysUser = sysUserService.getUserByIdentifyId( identifyId ) ;

        return UtilBean.copyBean(sysUser, CloudUser.class);
    }

    @Override
    @PostMapping(value = "/list/byorgcode")
    public List<CloudUser> queryOrgUsersByCode(@RequestParam(name = "source") String source ,
                                        @RequestParam(name = "orgCode") String orgCode){
        Assert.hasText( orgCode , "组织机构编码不能为空" );

        SysOrg sysOrg = sysOrgService.getByCode( source , orgCode ) ;
        if( sysOrg==null ){
            return null ;
        }

        return this.queryOrgUsers( source , sysOrg.getId() ) ;
    }

    @Override
    public List<CloudUser> queryOrgUsers(@RequestParam(name = "source") String source ,
                                         @RequestParam(name = "orgid") Long orgId) {

        List<SysUser> sysUsers = sysUserService.queryByOrg( source , orgId ) ;

        return UtilBean.copyBeans( sysUsers , CloudUser.class ) ;
    }

    @Override
    public CloudOrg queryUserOrg(@RequestParam(name = "source") String source ,
                                 @RequestParam(name = "userid") Long userId) {

        SysOrg sysOrg = sysOrgService.queryUserOrg( source , userId ) ;

        return UtilBean.copyBean( sysOrg , CloudOrg.class ) ;
    }
}
