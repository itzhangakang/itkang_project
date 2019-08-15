app.service("payService", function ($http) {

    // 调用微信接口生成支付二维码
    this.createNative = function () {
        return $http.get("pay/createNative?r" + Math.random())
    };

    // 调用微信查询订单状态接口，查看是否支付成功
    this.queryPayStatus = function (out_trade_no) {
        return $http.get("pay/queryPayStatus?out_trade_no=" + out_trade_no);
    }
})