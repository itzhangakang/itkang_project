package com.itkang.itkang_utils.utis.timingUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;

/**   
 * 功能描述：定时任务调用工具类
 * @Package: com.baosight.minmetals 
 * @author:
 * @date: 2019年2月25日 下午4:57:02 
 */

//@Component // 项目启动时启动
//@Order(1)
@PropertySource(value = "classpath:cron.properties") // 读取配置文件中的定时参数
@Slf4j
public  class CommandLineRunnerRun implements CommandLineRunner {

	@Autowired
	private TimerTask timerTask;
	@Value("${job.corn.10}")
	private String corn;

	
	@Override
	public  void run(String... args) throws Exception {
		try {
			log.info("     ---------------------- 启动定时任务 --------------------------");
			TaskSchedulerUtil.startScheduled("putout", ()->{
				try {
					timerTask.putout();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, corn); // corn：定时参数

			TaskSchedulerUtil.startScheduled("transfer", ()->{
				try {
					timerTask.transfer();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, corn);

			TaskSchedulerUtil.startScheduled("inBill", ()->{
				try {
					timerTask.inBill();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, corn);

			TaskSchedulerUtil.startScheduled("goodsStock", ()->{
				try {
					timerTask.goodsStock();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, corn);
		} catch (Exception e){
			log.error("     ---------------------- 启动定时任务失败 --------------------------" , e);
		}
	}

	
}