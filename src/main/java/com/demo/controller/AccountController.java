package com.demo.controller;

import com.sany.truck.pub.pay.account.OpenAccountSendMsgRequest;
import com.sany.truck.pub.pay.account.OpenAccountService;
import com.sany.truck.pub.pay.account.OpenPersonAccountRequest;
import com.sany.truck.pub.pay.account.QueryPersonAccountRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/account")
@Api(tags = {"简易开户"})
public class AccountController {

    @Autowired
    private OpenAccountService openAccountService;

    @ApiOperation("简易开户发送验证码")
    @ResponseBody
    @GetMapping("/sendSMSForOpenAccount")
    public String sendSMSForOpenAccount() throws Exception {
        OpenAccountSendMsgRequest request = new OpenAccountSendMsgRequest("17512029876");
        return this.openAccountService.sendSMSForOpenAccount(request);
    }

    @ApiOperation("个人简易开户")
    @ResponseBody
    @GetMapping("/openPersonAccount")
    public Object openPersonAccount() throws Exception {

        String certNo = "430621199001012122"; //身份证
        String customerName = "姚芳芳"; //身份证
        String teleNo = "13211223345"; //电话号码
        String smsCode = "123456"; //短信
        OpenPersonAccountRequest request = new OpenPersonAccountRequest(certNo, customerName, teleNo, smsCode);
        return this.openAccountService.openPersonAccount(request);
    }

    @ApiOperation("查询个人账户信息")
    @ResponseBody
    @GetMapping("/queryPersonAccount")
    public Object queryPersonAccount() throws Exception {
        QueryPersonAccountRequest request = new QueryPersonAccountRequest();
        request.setCertNo("430621187809098765");
        return this.openAccountService.queryPersonAccount(request);
    }

}
