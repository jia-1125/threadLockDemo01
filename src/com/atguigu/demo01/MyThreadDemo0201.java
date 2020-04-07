package com.atguigu.demo01;

//生产者和消费者 解决生产者供应太多，避免出现生产过量，
//要求达到 产1消1  以下例子提供四个线程 2产 2消模拟   同步方法判断 避免使用if（会导致消1连产。唤醒后不在判断继续向下执行）  应用while（唤醒后继续判断） 解决以上问题
class Cack {
    private int number = 0;
    //生产
    public synchronized void production() throws Exception {
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "生产" + number + "个");
        //生产后唤醒消费者消费 通知
        this.notifyAll();
    }
    //消费
    public synchronized void consumer() throws Exception {
        //判断如果没有货了就等待
        while (number == 0) {
            this.wait();
        }
        //有货就开始消费
        number--;
        System.out.println(Thread.currentThread().getName() + "消费" + number + "个");
        //唤醒生产者生产
        this.notifyAll();
    }
}

public class MyThreadDemo0201 {
    public static void main(String[] args) {
        //创建访问的公共资源类对象
        Cack cack = new Cack();
        //制造线程进行生产和消费
        new Thread(() -> {
            try {
                for (int i = 1; i < 33; i++) {
                    cack.production();
                }
            } catch (Exception e) {
            }
        }, "生产者A").start();
        new Thread(() -> {
            try {
                for (int i = 1; i < 33; i++) {
                    cack.consumer();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "消费者A").start();

        new Thread(() -> {
            try {
                for (int i = 1; i < 33; i++) {
                    cack.production();
                }
            } catch (Exception e) {
            }
        }, "生产者B").start();

        new Thread(() -> {
            try {
                for (int i = 1; i < 33; i++) {
                    cack.consumer();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "消费者B").start();

    }
}
