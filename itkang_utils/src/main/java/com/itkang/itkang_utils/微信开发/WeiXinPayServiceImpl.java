package com.itkang.itkang_utils.微信开发;

import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("weiXinPayService")
public class WeiXinPayServiceImpl implements WeiXinPayService {

    //@Value("${appid}")
    private String appid = "wx8397f8696b538317";
    //@Value("${partner}")
    private String partner = "1473426802";
   // @Value("${partnerkey}")
    private String partnerkey = "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb";
    //@Value("${notifyurl}")
    private String notifyurl = "http://xxx.ngrok.io/WeChatPay/WeChatPayNotify";


    @Override
    public Map createNative(String out_trade_no, String total_fee) {

        Map<String, String> param = new HashMap();
        try {
            // 1.封装需要发送的参数
            Map<String, String> map = new HashMap<>();
            // 公众账号ID
            map.put("appid", appid);
            // 商户号
            map.put("mch_id", partner);
            // 随机字符串
            map.put("nonce_str", WXPayUtil.generateNonceStr());
            // 签名
            //  map.put("",);
            // 商品描述
            map.put("body", "京东");
            // 商品订单号
            map.put("out_trade_no", out_trade_no);
            // 金额
            map.put("total_fee", total_fee);
            // 终端IP
            map.put("spbill_create_ip", "127.0.0.1");
            // 通知地址
            map.put("notify_url", notifyurl);
            // 交易类型
            map.put("trade_type", "NATIVE ");

            // 转换为xml后发送
            String signedXml = WXPayUtil.generateSignedXml(map, partnerkey);

            // 2.发送统一下单到支付系统
            HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            httpClient.setHttps(true);
            httpClient.setXmlParam(signedXml);
            httpClient.post();

            // 3.获取返回结果
            String content = httpClient.getContent();
            System.err.println("调用微信下单返回：" + content);

            Map<String, String> xmlToMap = WXPayUtil.xmlToMap(content);

            param.put("return_code", xmlToMap.get("return_code")); // 状态码
            param.put("out_trade_no", out_trade_no); // 订单号
            param.put("total_fee", total_fee); // 价格
            param.put("code_url", xmlToMap.get("code_url"));

            System.out.println("+++" + xmlToMap.get("code_url"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return param;
    }

    /**
     * 查询支付状态的方法
     *
     * @param out_trade_no
     * @return
     */
    @Override
    public Map<String, String> queryPayStatus(String out_trade_no) {

        try {
            Map<String, String> map = new HashMap<>();
            // 公众账号ID
            map.put("appid", appid);
            // 商户号
            map.put("mch_id", partner);
            // 商户订单号	out_trade_no
            map.put("out_trade_no", out_trade_no);
            // 随机字符串
            map.put("nonce_str", WXPayUtil.generateNonceStr());
            // 签名	sign

            // 转换为xml格式后发送
            String xml = WXPayUtil.generateSignedXml(map, partnerkey);

            // 发送消息
            HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            httpClient.setHttps(true);
            httpClient.setXmlParam(xml);
            httpClient.post();

            // 获取结果
            String content = httpClient.getContent();
            System.err.println("返回结果：" + content);

            // 封装返回
            return WXPayUtil.xmlToMap(content);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 关闭微信支付订单
     *
     * @param out_trade_no
     * @return
     */
    @Override
    public Map closePay(String out_trade_no) {
        try {
            Map<String, String> map = new HashMap<>();
            // 公众账号ID
            map.put("appid", appid);
            // 商户号
            map.put("mch_id", partner);
            // 商户订单号	out_trade_no
            map.put("out_trade_no", out_trade_no);
            // 随机字符串
            map.put("nonce_str", WXPayUtil.generateNonceStr());
            // 签名	sign

            // 转化为xml发送请求
            String xml = WXPayUtil.generateSignedXml(map, partnerkey);

            // 发送消息
            HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/closeorder");
            httpClient.setXmlParam(xml);
            httpClient.setHttps(true);
            httpClient.post();

            // 获取结果
            String content = httpClient.getContent();
            System.out.println("订单关闭" + content);

            //  将结果转为Map 返回
            return WXPayUtil.xmlToMap(content);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
