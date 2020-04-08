package com.atguigu.demo01;


import com.sun.xml.internal.bind.v2.model.core.ID;
import jdk.nashorn.internal.ir.ReturnNode;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class BusTicket {
    private int number = 30;
    private ReentrantLock reentrantLock = new ReentrantLock();//默认是非公平锁

    public void sale() throws InterruptedException {
//        reentrantLock.lock();
        reentrantLock.tryLock(2, TimeUnit.SECONDS);
//        reentrantLock.tryLock();
//        reentrantLock.lock();

//        TimeUnit.SECONDS.sleep(1);
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "\t卖出第: " + (number--) + "\t还剩下: " + number);
        }
        if (reentrantLock.isHeldByCurrentThread()){
            reentrantLock.unlock();
        }
//        reentrantLock.unlock();
    }
}
//
//1. ReentrantLock可以实现公平锁和非公平锁
//        2. ReentrantLock默认实现的是非公平锁
//        3. ReentrantLock的获取锁和释放锁必须成对出现，锁了几次，也要释放几次
//        4. 释放锁的操作必须放在ﬁnally中执行
//        5. 实例方法tryLock()获会尝试获取锁，会立即返回，返回值表示是否获取成功
//        6. 实例方法tryLock(long timeout, TimeUnit unit)会在指定的时间内尝试获取锁，
//        指定的时间内是否能够获取锁，都会返回，返回值表示是否获取锁成功
//读写分离
//解决并发写，可以并发读
public class ReenTrantLockDemo01 {
    public static void main(String[] args) {
        BusTicket busTicket = new BusTicket();
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                for (int j = 1; j < 50; j++) {
                    try {
                        busTicket.sale();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
