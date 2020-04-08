package com.atguigu.demo01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {
    private int flag = 1;
    private Lock lock = new ReentrantLock();//默认不公平锁
    private Condition condition = lock.newCondition();
    public void prin5() {
        lock.lock();
        //判断
        try {
            while (flag != 1) {
                condition.await();
            }
            //干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //只通知B系统
            flag=2;
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }
    }
    public void prin10() {
        lock.lock();
        //判断
        try {
            while (flag != 2) {
                condition.await();
            }
            //干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            flag=3;
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void prin15() {
        lock.lock();
        try {
            //判断
            while (flag != 3) {
                //干活
               condition.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            flag=1;
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}//资源类

// 多线程之间按顺序调用，实现A->B->C
//         * 三个线程启动，要求如下：
//         *
//         * AA打印5次，BB打印10次，CC打印15次
//         * 接着
//         * AA打印5次，BB打印10次，CC打印15次
//         * ......三个线程来10轮
//         *
//         * 精确通知，按照顺序走
//         *
//         * 1 线程操作资源类
//         * 2 判断、干活、通知
//         * 3 while
//         * 4 记得修改线程标志位
public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource shareResource=new ShareResource();
        new Thread(() -> {
            shareResource.prin5();
        },"A线程").start();
        new Thread(() -> {
            shareResource.prin10();
        },"B线程").start();
        new Thread(() -> {
            shareResource.prin15();
        },"C线程").start();
    }
}
