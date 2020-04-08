package com.atguigu.demo01;


import java.util.concurrent.CountDownLatch;

// 题目：要求如下
//         *   多个线程协同干活，要求到最后一个线程为零后，才能启动主线程。
//         *   做一个减少一个，清零后才能启动主线程，逐个做减法。
//         *
//         *   7个同学在一个教室上晚自习，要求班长锁门，必须要最后一个同学都离开教室了，班车才可以
//         *   关门走人。

//一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //初始化一个线程数
        CountDownLatch countDownLatch=new CountDownLatch(7);
        for (int i = 1; i <=7 ; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "出教室");
                //计数器减一
                countDownLatch.countDown();
            },"线程"+i).start();
        }
        //如果计数器不为0就不放行
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "班长锁门");
    }
}
