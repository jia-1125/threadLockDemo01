package com.atguigu.demo01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Commodity {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    //声明condition对象监视器
    private Condition condition = lock.newCondition();

    //生产
    public void producer() throws Exception {
        lock.lock();
        //判断是否还有产品 有就停产等消费者消费完再生产避免存活
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "生产" + number + "个");
            //唤醒所有等待的线程
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    //消费
    public void coustomer() throws Exception {
        lock.lock();
        try {
            while (number == 0) {
                //没有产品停止消费
                condition.await();
            }
            //开始消费产品
            number--;
            System.out.println(Thread.currentThread().getName() + "消费:" + number + "个");
            //消费后通知生产者生产 唤醒生产者线程
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }
    }
}

//使用lock锁和Condition等待和唤醒功能 实现生产者和消费者的运行场景
public class LockProAndCus {
    public static void main(String[] args) {
        //资源对象
        Commodity commodity = new Commodity();
        new Thread(
                () -> {
                    for (int i = 1; i < 33; i++) {
                        try {
                            Thread.sleep(200);
                            commodity.producer();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, "生产者A").start();
        new Thread(() -> {
            for (int i = 1; i < 33; i++) {
                try {
                    Thread.sleep(200);
                    commodity.coustomer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "消费者A").start();
        new Thread(
                () -> {
                    for (int i = 1; i < 33; i++) {
                        try {
                            Thread.sleep(400);
                            commodity.producer();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, "生产者B").start();

        new Thread(() -> {
            for (int i = 1; i < 33; i++) {
                try {
                    Thread.sleep(400);
                    commodity.coustomer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "消费者B").start();

    }

}
