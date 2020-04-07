package com.atguigu.demo01;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//原始方案
class Ticket implements Runnable {
    private int number = 30;
    static Object objectLock = new Object();
    @Override
    public void run() {
        for (int i = 1; i < 33; i++) {
            synchronized (objectLock) {
                if (number > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖出第" + (number--) + "张票,还剩" + number + "张");
                }
            }
        }
    }
}
//lock方案
class Ticket1 {
    private int number = 30;
    private Lock lock = new ReentrantLock();
    public void sale() {
        for (int i = 1; i < 33; i++) {
        lock.lock();
        try {
            if (number>0){
                System.out.println(Thread.currentThread().getName() + "卖出第" + (number--) + "张票,还剩" + number + "张");
            }
        } finally {
            //释放锁
            lock.unlock();
        }
        }
    }
}

public class MyThread {
    public static void main(String[] args) {
        //原始方案
//        Ticket ticket=new Ticket();
//        Thread t1 = new Thread(ticket, "线程1");
//        Thread t2 = new Thread(ticket, "线程2");
//        Thread t3 = new Thread(ticket, "线程3");
//        t1.start();
//        t2.start();
//        t3.start();
        //lock方案一
//        Ticket1 ticket=new Ticket1();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ticket.sale();
//            }
//        },"线程1").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ticket.sale();
//            }
//        },"线程2").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ticket.sale();
//            }
//        },"线程3").start();
        //lock方案二
        Ticket1 ticket=new Ticket1();
        new Thread(()->{ticket.sale();},"线程1").start();
        new Thread(()->{ticket.sale();},"线程2").start();
        new Thread(()->{ticket.sale();},"线程3").start();


    }

}
