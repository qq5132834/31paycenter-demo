package com.demo.controller;

import com.sany.truck.pub.pay.account.OpenAccountSendMsgRequest;
import com.sany.truck.pub.pay.account.OpenAccountService;
import com.sany.truck.pub.pay.consume.PayCenterConsumeRequest;
import com.sany.truck.pub.pay.consume.PayCenterConsumeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/consume")
@Api(tags = {"消费"})
public class ConsumeController {

    @Autowired
    private PayCenterConsumeService payCenterConsumeService;

    @ApiOperation("预支付")
    @ResponseBody
    @GetMapping("/consume")
    public String consume() throws Exception {
        PayCenterConsumeRequest request = new PayCenterConsumeRequest();
        request.setMerchantCode("A001");
        request.setAmount(100l);
        request.setConsumeOrderId("ORDERID112358");
        request.setConsumeOrderName("订单名称");
        request.setTimestamp(System.currentTimeMillis());
        return this.payCenterConsumeService.consumePrepay(request);
    }

}
