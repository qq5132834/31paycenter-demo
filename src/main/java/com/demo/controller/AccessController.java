package com.demo.controller;

import com.sany.truck.pub.pay.access.AccessRequest;
import com.sany.truck.pub.pay.access.BaseInfoRequest;
import com.sany.truck.pub.pay.access.PayCenterAccessService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/access")
@Api(tags = {"访问权限"})
public class AccessController {

    @Autowired
    private PayCenterAccessService payCenterAccessService;

    @ResponseBody
    @GetMapping("/ticket")
    public String ticket() throws Exception {
        AccessRequest request = new AccessRequest();
        request.setDeptId("deptId");
        request.setDeptName("deptName");
        request.setOrgId("orgId");
        request.setOrgName("orgName");
        request.setUserId("userId");
        request.setUserName("userName");
        String ticket = this.payCenterAccessService.getTicket(request);
        return ticket;
    }

    @ResponseBody
    @GetMapping("/baseinfo")
    public Object baseinfo() throws Exception {

        AccessRequest accessRequest = new AccessRequest();
        accessRequest.setDeptId("deptId");
        accessRequest.setDeptName("deptName");
        accessRequest.setOrgId("orgId");
        accessRequest.setOrgName("orgName");
        accessRequest.setUserId("userId");
        accessRequest.setUserName("userName");
        String ticket = this.payCenterAccessService.getTicket(accessRequest);

        //
        BaseInfoRequest baseInfoRequest = new BaseInfoRequest();
        baseInfoRequest.setCertNo("130562198909100110");
        baseInfoRequest.setTicket(ticket);
        String info = this.payCenterAccessService.getBaseInfo(baseInfoRequest);
        return info;
    }

}
