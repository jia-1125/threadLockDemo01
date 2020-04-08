package com.atguigu.demo01;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args)throws  Exception {
//        它将在给定数量的参与者（线程）处于等待状态时启动
        //由最后等待的线程发起执行
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println(Thread.currentThread().getName() + "召唤神龙");
        });
        for (int i = 1; i <=7 ; i++) {
            final int tempI=i;
            new Thread(() -> {
                try {
                    //累加到初始化指定的等待的计数 方可执行
                    System.out.println(Thread.currentThread().getName() + "收集到第"+tempI+"龙珠");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
