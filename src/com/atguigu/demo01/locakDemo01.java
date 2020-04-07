package com.atguigu.demo01;


import java.util.concurrent.TimeUnit;

class Phone {

    public  static synchronized void sendEmail() throws Exception {
//        TimeUnit.SECONDS.sleep(6);
        System.out.println("**********sendEmail");
    }
    public   synchronized void sendSMS() throws Exception {
        System.out.println("**********sendSMS");
    }
    public void WatchTV(){
        System.out.println("***********WatchTV");
    }
}


//八锁原理
//1. 同一对象 方法同为synchronized修饰的 锁对象（资源类） 谁先抢到谁执行  例一先执行email 其次SMS
//2.同一对象 一个为synchronized修饰  一个普通方法  锁对象对普通方法 (普通方法不参与锁机制)没影响该执行还得执行
//3. 同一对象 方法同为 static synchronized 修饰的方法  锁类 谁先抢到锁谁优先执行 例一先执行email 其次SMS
//4. 两个对象时， 对象中的两个方法 ：   普通的synchronized 和普通的synchronized   相互不影响，你抢你的对象锁,我抢我的对象锁，谁快谁先执行，
//5. 同一对象    一个static synchronized 方法 一个普通的synchronized 方法 ,执行时相互不影响， (不加static 就是对象资源锁，加static就是类锁)
//锁的不是同一级别，不影响
//6.两个对象时。一个static synchronized   一个static synchronized   由于是锁的类 属于同一级别的锁，不管是多少个对象只要类型相同，就会存在抢锁，谁抢到谁执行
//7. 两个对象时,一个static synchronized  一个普通的synchronized  由于static 锁的是类， 普通的synchronized 锁的是对象， 两个的不是同一级别的锁，所以不存在资源抢夺问题
//8.标准方法，email 同步方法体中不加睡眠时间。 谁先强到锁谁就执行，另一个门口排队等待
public class locakDemo01 {
    public static void main(String[] args) throws InterruptedException {
        Phone p = new Phone();
        Phone p2=new Phone();
        new Thread(
                () -> {
                    try {
//                        p.sendEmail();
                        p2.sendEmail();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                , "线程1").start();
        Thread.sleep(3000);
        new Thread(() -> {
            try {
                p.sendSMS();
//                p.WatchTV();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "线程2").start();

    }
}
