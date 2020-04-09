package com.atguigu.demo01;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
//semaphore 类的使用
//模拟停车场案例
public class SemaphoreDemo01 {
    public static void main(String[] args) {
        //初始许可证3个
        Semaphore semaphore=new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                boolean flag=false;
                try {
                    //获取许可证
                    semaphore.acquire();
                    flag=true;
                    System.out.println(Thread.currentThread().getName() + "\t 抢占停车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "\t 离开停车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (flag){
                        //释放许可
                        semaphore.release();
                    }
                }
            },"线程"+i).start();
        }
    }
}
