package com.itkang.itkang_utils.utis.timingUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@PropertySource(value = "classpath:cron.properties")
public class TimerTask {

    public void putout() throws Exception {
        log.info("   **************** 执行定时任务 wms 上传出库信息  *****************   ");

        log.info("   **************** 调用结束  出库同步  ***************** 本次处理： " + "条记录");
    }


    //@Scheduled(cron = "${job.corn.w5}")
    public void transfer() throws Exception {
        log.info("   **************** 执行定时任务 wms 上传过户信息  *****************   ");

        log.info("   **************** 调用结束  过户同步  ***************** 本次处理： " + "条记录");
    }


    //@Scheduled(cron = "${job.corn.w5}")
    public void inBill() throws Exception {
        log.info("   **************** 执行定时任务 wms 上传入库实绩信息  *****************   ");

        log.info("   **************** 调用结束   入库实绩  ***************** 本次处理： " + "条记录");
    }


    //@Scheduled(cron = "${job.corn.w5}")
    public void goodsStock() throws Exception {
        log.info("   **************** 执行定时任务 wms 上传库存同步信息  *****************   ");

        log.info("   **************** 调用结束  库存同步  ***************** 本次处理： " + "条记录");
    }

}
