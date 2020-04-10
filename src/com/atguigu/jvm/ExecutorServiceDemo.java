package com.atguigu.jvm;

import java.util.concurrent.*;

//手写线程池参数拒绝策略三种情况分析
//CallerRunsPolicy 如果饱和了直接抛异常
//AbortPolicy 如果饱和了，其余的请求从哪里来就回哪里去
//DiscardPolicy 丢弃最后的，未被执行的任务
//DiscardOldestPolicy 来的早不如来得巧，
public class ExecutorServiceDemo {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(2, 5,
                2L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
//        Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy();
//        Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
//        Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 1; i <=9 ; i++) {
                final int tmpI=i;
                executorService.submit(()->{
                    System.out.println(Thread.currentThread().getName() + "\t 执行任务"+tmpI);
//                    try {
//                        TimeUnit.SECONDS.sleep(5);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
