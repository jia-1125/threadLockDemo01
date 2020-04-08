package com.atguigu.demo01;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Cache {
    volatile Map<String, String> map = new HashMap<String, String>();
    //重入锁
    private Lock lock=new ReentrantLock();
    //读写锁
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    //第一种使用ReentranLock锁 解决的并发写问题，不能并发读
//    public void write(String key, String value) {
//        lock.lock();
//        try {
//            System.out.println(Thread.currentThread().getName() + "\t 准备写入");
//            map.put(key, value);
//            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public void read(String key) {
//        lock.lock();
//        try {
//            System.out.println(Thread.currentThread().getName() + "\t 准备读取");
//            String result = map.get(key);
//            System.out.println(Thread.currentThread().getName() + "\t 读取成功:" + result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }
//    }
//第二种使用读写锁处理
    public void write(String key, String value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 准备写入");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void read(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 准备读取");
            String result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取成功:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

//读写锁的使用 ReadWriteLock
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        Cache cache=new Cache();
        for (int i = 1; i <=10 ; i++) {
            final int tmpI=i;
            new Thread(() -> {
                cache.write(tmpI+"",tmpI+"");
            },"写A"+i).start();
        }
        for (int i = 1; i <=10 ; i++) {
            final int tmpI=i;
            new Thread(() -> {
                cache.read(tmpI+"");
            },"读A"+i).start();
        }
    }
}
