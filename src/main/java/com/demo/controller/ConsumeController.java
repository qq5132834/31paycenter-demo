package com.demo.controller;

import com.sany.truck.pub.pay.consume.PayCenterConsumePrePayRequest;
import com.sany.truck.pub.pay.consume.PayCenterConsumeService;
import com.sany.truck.pub.pay.consume.PayCenterConsumeToPayRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consume")
@Api(tags = {"消费"})
public class ConsumeController {

    @Autowired
    private PayCenterConsumeService payCenterConsumeService;

    @ApiOperation("预支付")
    @ResponseBody
    @GetMapping("/prepay")
    public String prepay() throws Exception {
        PayCenterConsumePrePayRequest request = new PayCenterConsumePrePayRequest();
        request.setMerchantCode("A001");
        request.setAmount(100l);
        request.setConsumeOrderId("ORDERID112358");
        request.setConsumeOrderName("订单名称");
        request.setTimestamp(System.currentTimeMillis());
        request.setCustomerAccontNo("1007001002004");
        return this.payCenterConsumeService.consumePrepay(request);
    }

    @ApiOperation("结算宝支付")
    @ResponseBody
    @PostMapping("/topay")
    public String topay(@RequestBody PayCenterConsumeToPayRequest request) throws Exception {
        request.setPayMethod(2); //1密码支付，2免密支付
        request.setPayAccountNo("123456"); //支付账户
        return this.payCenterConsumeService.consumeTopay(request);
    }

}
