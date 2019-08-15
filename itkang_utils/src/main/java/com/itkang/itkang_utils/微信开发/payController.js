app.controller("payController", function ($scope, $interval, $location, payService) {

    // http://item.pinyougou.com/149187842868031.html
    // 页面初始化时候根据订单生成二维码和显示总金额
    $scope.createNative = function () {
        payService.createNative().success(function (response) {
            //alert(JSON.stringify(response))
            if ("SUCCESS" == response.return_code) {
                $scope.money = (response.total_fee / 100).toFixed(2); // 金额
                $scope.out_trade_no = response.out_trade_no // 订单号

                // 生成二维码
                var qr = new QRious({
                    element: document.getElementById('qrious'),
                    size: 250,
                    level: 'H',
                    value: response.code_url
                });

                // 调用查询支付状态的方法
                queryPayStatus(response.out_trade_no);

            } else {
                alert("生成支付二维码失败！")
            }
        })
    }


    // 查询支付状态的方法
    queryPayStatus = function (out_trade_no) {
        payService.queryPayStatus(out_trade_no).success(function (response) {
            if (response.success) {
                // 跳转到支付成功页面
                location.href = "paysuccess.html#?money=" + $scope.money; // 跳转时候带到地址栏
            } else {
                if (response.message == "二维码过期") {
                    // 重新调用生成二维码方法
                    $scope.createNative();
                } else {
                    location.href = "payfail.html";
                }
            }
        })
    }

    // 获得支付金额,取出支付成功后浏览器跳转页面时携带的金额数据
    $scope.getMoney = function () {
        return $location.search()['money'];
    }


    // 设置支付过期时间 30分钟
    var endTime = (new Date().getTime()) + (1 * 60 * 1000); // 当前时间＋30分钟
    // 获得当前时间
    var newTime = new Date().getTime();
    // 剩余秒数
    $scope.second = (endTime - newTime) / 1000;

    var time = $interval(function () {
        if ($scope.second > 0) {
            $scope.second = $scope.second - 1;
            // 格式化时间
            $scope.timeString = $scope.convertTimeString($scope.second);
        } else {
            alert("支付超时，订单被取消");
            // 倒计时结束
            $interval.cancel(tiem);
        }

    }, 1000)

    // 格式化时间的方法
    $scope.convertTimeString = function (second) {
        // 分钟
        var min = Math.floor(second / 60);
        // 秒
        var ss = Math.floor(second - min * 60);
        // 拼接时间
        var timeString = "";
        min < 10 ? "0" + min : min;
        ss < 10 ? "0" + ss : ss;
        return timeString += min + "分" + ss + "秒";
    }

})