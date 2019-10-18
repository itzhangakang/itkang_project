package com.itkang.itkang_utils.utis.timingUtils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

/**
 * @author Mr.kang
 * Created by - 林夕
 * @date 2019/10/18 15:08
 * @explain 定时任务调用测试
 */

public class TestTiming {

    @Autowired
    private CommandLineRunner commandLineRunner;

    @Test
    public void test() {
        try {
            commandLineRunner.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private TimerTask timerTask;

    @Value("${job.corn.10}")
    private String corn = "*/5 * * * * ?";

    @Test
    public void test2() {
        try {
            TaskSchedulerUtil.startScheduled("transfer", () -> {
                try {
                    timerTask.putout();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, corn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

