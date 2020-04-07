package com.atguigu.demo01;

class Shop{
    private int number=0;
    public synchronized void increment() throws  Exception{
        if (number!=0){
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll();
    }
    public  synchronized void decrement() throws  Exception{
        if (number==0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //通知
        this.notifyAll();
    }
}

public class MyThreadDemo02 {
    public static void main(String[] args) {
        Shop s=new Shop();
        new Thread(()->{
            for (int i = 1; i <33; i++) {
                try {
                    s.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"线程1").start();
        new Thread(()->{
            for (int i = 1; i <33; i++) {
                try {
                    s.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"线程2").start();
    }
}
