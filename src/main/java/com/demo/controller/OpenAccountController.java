package com.demo.controller;

import com.sany.truck.pub.pay.account.OpenAccountService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = {"简易开户"})
public class OpenAccountController {

    @Autowired
    private OpenAccountService openAccountService;

    @ResponseBody
    @GetMapping("/openPersonAccount")
    public String openPersonAccount(){
        return "Hello World";
    }

}
