package com.itkang.itkang_utils.utis.TaskScheduler;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;


/**
 * @author 韩正禹
 * 2019年6月27日 下午3:45:53
 */
public class TaskSchedulerUtil {


	private static int schedulerPoolSize = 20;
    private static int schedulerAwaitTerminationSeconds = 180;
    private static int executorPoolSize = 5;
    private static int executorPoolMaxSize = 10;
    private static ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private static Map<String, ScheduledFuture<?>> futureMap;
    private static ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private static Map<String, Runnable> taskMap;
    static {
        futureMap = new HashMap<>();
        taskMap = new HashMap<>();
    }


    //开启定时任务
    public static ScheduledFuture<?> startScheduled(String taskId, Runnable task, String corn) throws Exception
    {
        initThreadPoolTaskScheduler();
        if (null != futureMap && futureMap.containsKey(taskId)) {
            throw new Exception("duplicate task Id. already exist.");
        }
        ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(task, new CronTrigger(corn));
        futureMap.put(taskId, schedule);
        taskMap.put(taskId, task);
        return schedule;
    }


    //关闭定时任务
    public static void cancelScheduled(String taskId) throws Exception {
        if (null != futureMap && futureMap.containsKey(taskId)){
            ScheduledFuture<?> future = futureMap.get(taskId);
            future.cancel(true);
            futureMap.remove(taskId);
            taskMap.remove(taskId);
        } else {
            throw new Exception("no task found for taskId ["+taskId+"] .");
        }
    }


    //执行一个任务
    public static void executeTask(Runnable runnable){
        initThreadPoolTaskExecutor();
        threadPoolTaskExecutor.execute(runnable);
    }

    //触发一次任务
    public static void triggerTask(String taskId) throws Exception{
        initThreadPoolTaskExecutor();
        if(!taskMap.containsKey(taskId)){
            throw new Exception("taskId not found.");
        }
        threadPoolTaskExecutor.execute(taskMap.get(taskId));
    }


    //初始化 ThreadPoolTaskScheduler
    private static void initThreadPoolTaskScheduler(){
        if (null == threadPoolTaskScheduler){
            threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
            threadPoolTaskScheduler.setPoolSize(schedulerPoolSize);
            threadPoolTaskScheduler.setAwaitTerminationSeconds(schedulerAwaitTerminationSeconds);
            threadPoolTaskScheduler.setThreadNamePrefix("thread-pool-scheduler-task-");
            threadPoolTaskScheduler.initialize();
        }
    }


    //初始化 ThreadPoolTaskExecutor
    private static void initThreadPoolTaskExecutor(){
        if(threadPoolTaskExecutor == null){
            threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
            threadPoolTaskExecutor.setThreadNamePrefix("thread-pool-task-executor-");
            threadPoolTaskExecutor.setCorePoolSize(executorPoolSize);
            threadPoolTaskExecutor.setMaxPoolSize(executorPoolMaxSize);
            //threadPoolTaskExecutor.setQueueCapacity(25);	//队列不限
            threadPoolTaskExecutor.initialize();
        }
    }



}
