package com.itkang.itkang_utils.微信开发;

import java.util.Map;


public interface WeiXinPayService {

    /**
     * 获得支付二维码地址
     * @param out_trade_no
     * @param total_fee
     * @return
     */
    Map createNative(String out_trade_no, String total_fee);

    /**
     * 查询定单支付状态
     * @param out_trade_no
     * @return
     */
    Map<String,String> queryPayStatus(String out_trade_no);

    /**
     * 关闭订单 默认微信2小时后关闭，如需要可以自己设置
     * @param out_trade_no
     * @return
     */
    Map closePay(String out_trade_no);
}
