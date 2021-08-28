package com.demo.controller;

import com.sany.truck.pub.pay.consume.*;
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

    @ApiOperation("授权代理支付")
    @ResponseBody
    @GetMapping("/authorizationAccount")
    public String authorizationAccount() throws Exception {
        AuthorizationForAccountRequest request = new AuthorizationForAccountRequest();
        request.setMerchantCode("A0001"); //商家代码
        request.setAuthorizationAccountNo("1007001002004"); //授权账户
        request.setAccountNo("1007001002003"); //被授权账户
        request.setCertNo("430621199001013456"); //被授权账户身份证
        request.setCustomerName("张三"); //被授权账户名字
        request.setMonthMaxAmount(500000l); //被授权每月最大金额【单位分】
        request.setSmsCode("123456"); //验证码
        return this.payCenterConsumeService.authorizatonAccount(request);
    }

    @ApiOperation("物料免密支付")
    @ResponseBody
    @GetMapping("/authorizatonMaterial")
    public String authorizationMaterial() throws Exception {

        AuthorizationForMaterialRequest request = new AuthorizationForMaterialRequest();
        request.setMerchantCode("085c5cbc8c2744078ced7365ce5cc518"); //商家代码
        request.setAuthorizationAccountNo("0070018700003760004"); //授权账户
        request.setMaterialCode("湘A0001"); //物料代码
        request.setMaterialDesc("姚芳芳的小摩托"); //物料描述
        request.setMonthMaxAmount(500000l); //被授权每月最大金额【单位分】
        request.setSmsCode("123456"); //验证码
        return this.payCenterConsumeService.authorizatonMaterial(request);
    }

    @ApiOperation("预支付")
    @ResponseBody
    @GetMapping("/prepay")
    public String prepay() throws Exception {
        PayCenterConsumePrePayRequest request = new PayCenterConsumePrePayRequest();
        request.setMerchantCode("085c5cbc8c2744078ced7365ce5cc518");
        request.setAmount(100l);
        request.setConsumeOrderId("ORDERID-" + System.currentTimeMillis());
        request.setConsumeOrderName("姚芳芳的小摩托充电交费");
        request.setTimestamp(System.currentTimeMillis());
        request.setCustomerAccontNo("1007001002004");
        return this.payCenterConsumeService.consumePrepay(request);
    }

    @ApiOperation("免密支付")
    @ResponseBody
    @PostMapping("/noSecretToPay")
    public String noSecretToPay(@RequestBody NoSecretToPayRequest noSecretToPayRequest) throws Exception {
        noSecretToPayRequest.setMerchantCode("085c5cbc8c2744078ced7365ce5cc518"); //行必达商家号代码
        noSecretToPayRequest.setPayProtocol("1630056383448");
        noSecretToPayRequest.setPaySecret("8f9f1b31d7344caf8e42f9530842794b");
        return this.payCenterConsumeService.consumeProxyToPay(noSecretToPayRequest);
    }

    @ApiOperation("退款")
    @ResponseBody
    @PostMapping("/refund")
    public String refund(String oldPayOrderId, Long amount) throws Exception {
        PayCenterRefundRequest request = new PayCenterRefundRequest();
        request.setMerchantCode("085c5cbc8c2744078ced7365ce5cc518");
        request.setRefundOrderId("REFUND-" + System.currentTimeMillis());
        request.setRemark("退款");
        request.setOldPayOrderId(oldPayOrderId);
        request.setAmount(amount);
        return this.payCenterConsumeService.consumeRefund(request);
    }

    @ApiOperation("查询支付状态")
    @ResponseBody
    @PostMapping("/searchConsumePay")
    public String searchConsumePay(SearchConsumePayRequest request) throws Exception {
        request.setMerchantCode("085c5cbc8c2744078ced7365ce5cc518");
        return this.payCenterConsumeService.searchConsumePay(request);
    }

    @ApiOperation("查询退款状态")
    @ResponseBody
    @PostMapping("/searchConsumeRefund")
    public String searchConsumeRefund(SearchConsumeRefundRequest request) throws Exception {
        request.setMerchantCode("085c5cbc8c2744078ced7365ce5cc518");
        return this.payCenterConsumeService.searchConsumeRefund(request);
    }

}
