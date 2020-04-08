package com.atguigu.demo01;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread1 implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("come in here");
        TimeUnit.SECONDS.sleep(3);
        return 1024;
    }
}

public class CallableDemo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread1 myThread1=new MyThread1();//创建实现Callable接口的类对象
        //创建一个间接实现Runnable接口 和构造方法需要传入一个Callable接口类型的参数  FutureTask
        FutureTask futureTask=new FutureTask(myThread1);
        //创建一个线程
        new Thread(
                futureTask,
        "线程1").start();
        //创建多个线程，执行call只是一次，第二次调用的使缓存
        new Thread(
                futureTask,"线程2"
        ).start();
        System.out.println(Thread.currentThread().getName()+":计算完成");
        //如何获取futureTask的方法中的返回值呢?
        System.out.println(futureTask.get());
    }
}
