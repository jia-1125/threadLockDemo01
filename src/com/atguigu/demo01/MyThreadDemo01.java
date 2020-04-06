package com.atguigu.demo01;

import com.sun.org.apache.bcel.internal.generic.LoadClass;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket implements Runnable {
    private int number = 30;
    static Object objectLock = new Object();

    @Override
    public void run() {
        for (int i = 0; i < 33; i++) {
            synchronized (objectLock) {
                if (number > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖出第" + (number--) + "张票,还剩余" + number);
                }
            }
        }
    }
}

class Ticket1{
    private int number=30;
    private Lock lock=new ReentrantLock();

    public void sale(){
        lock.lock();
            if (number>0){
                System.out.println(Thread.currentThread().getName() + "卖出第" + (number--) + "张票,还剩余" + number);
            }
        }
}

public class MyThreadDemo01 {
    public static void main(String[] args) {
//        Ticket t = new Ticket();
        Ticket1 t=new Ticket1();
//        Thread t1 = new Thread(t, "线程1");
//        Thread t2 = new Thread(t, "线程2");
//        Thread t3 = new Thread(t, "线程3");
//        t1.start();
//        t2.start();
//        t3.start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 1; i <33 ; i++) {
//                    t.sale();
//                }
//            }
//        },"线程1").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                t.sale();
//            }
//        },"线程2").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                t.sale();
//            }
//        },"线程3").start();

        new Thread(()->{ for (int i = 1; i <33 ; i++) t.sale();},"线程1").start();
        new Thread(()->{ for (int i = 1; i <33 ; i++) t.sale();},"线程1").start();
        new Thread(()->{ for (int i = 1; i <33 ; i++) t.sale();},"线程1").start();
    }







}
