package com.itkang.itkang_utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItkangUtilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItkangUtilsApplication.class, args);
        //test2();
    }

//    @Autowired
//    private static TimerTask timerTask;
//
//    @Value("${job.corn.10}")
//    private static String corn = "*/5 * * * * ?";
//
//    public static void test2() {
//        try {
//            TaskSchedulerUtil.startScheduled("transfer", () -> {
//                try {
//                    timerTask.putout();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }, corn);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
