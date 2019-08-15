package com.itkang.itkang_utils.微信开发;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private WeiXinPayService weiXinPayService;

    @Autowired
    //private OrderService orderService;

    @RequestMapping("/select")
    public String select(){
        String string = "访问成功！";
        System.out.println("访问成功！");
        return string;
    }

    /**
     * 调用统一下单接口生成订单     /pay/createNative
     *
     * @return
     */
    @RequestMapping("/createNative")
    public Map createNative() {

      // String userName = SecurityContextHolder.getContext().getAuthentication().getName();
/*
        // 获取订单号，通过当前登录用户获取该用户的订单
        TbPayLog payLog = orderService.selectPaytRedis(userName);
        String out_trade_no = payLog.getOutTradeNo();

        // 金额
        String total_fee = payLog.getTotalFee().toString();*/

        // 调用支付获得二维码地址
        return weiXinPayService.createNative("20150806125346", "88.8");

    }

    /**
     * 查看订单是否支付成功
     *
     * @param out_trade_no
     * @return
     */
    @RequestMapping("/queryPayStatus")
    public Result queryPayStatus(String out_trade_no) {

        Result result = null;

        // 记录查询请求的次数
        int count = 0;
        while (true) {
            Map<String, String> map = weiXinPayService.queryPayStatus(out_trade_no);

            if (map == null) {
                result = new Result(false, "支付出错！");
                break;
            }

            if (map.get("trade_state").equals("SUCCESS")) {
                result = new Result(true, "支付成功");

                // 支付成功后修改支付后的订单状态
                //orderService.updateStatus(out_trade_no, map.get("transaction_id"));

                break;
            }

            count++;
            if (count > 20) { // 一分钟后过期
                result = new Result(false, "二维码过期");
                break;
            }

            // 休眠三秒无限调用
            try {
                //每隔3秒查询
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
