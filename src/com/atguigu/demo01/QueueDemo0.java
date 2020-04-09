package com.atguigu.demo01;

import javax.sound.midi.Soundbank;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//阻塞队列的方法的使用和说明
public class QueueDemo0 {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> arrayBlockingQueue=new ArrayBlockingQueue(3);
//        BlockingQueue<String> linkBlockingQueue=new LinkedBlockingQueue<>();
        //add方法的使用 添加元素到队列
//        System.out.println(arrayBlockingQueue.add("a"));
//        System.out.println(arrayBlockingQueue.add("b"));
//        System.out.println(arrayBlockingQueue.add("c"));
        System.out.println("==============================");
        //超出指定容量大小 抛出IllegalStateException异常 Queue full 队列已满
//        System.out.println(arrayBlockingQueue.add("a"));
        //remove 方法的使用 从队列中移除元素
//        System.out.println(arrayBlockingQueue.remove());
//        System.out.println(arrayBlockingQueue.remove());
//        System.out.println(arrayBlockingQueue.remove());
        //查出元素范围 抛异常 NoSuchElementException 找不到移除的元素
//        System.out.println(arrayBlockingQueue.remove());
        System.out.println("==================================");

        //offer 如果超出指定容量 返回false
//        System.out.println(arrayBlockingQueue.offer("a"));
//        System.out.println(arrayBlockingQueue.offer("b"));
//        System.out.println(arrayBlockingQueue.offer("c"));
//        System.out.println(arrayBlockingQueue.offer("d"));
        //peek抛出首元素
//        System.out.println(arrayBlockingQueue.peek());

        //put 如果添加的元素超出容量 会等待  无返回值
        arrayBlockingQueue.put("a");
        arrayBlockingQueue.put("b");
        arrayBlockingQueue.put("c");
//        arrayBlockingQueue.put("d");

        //take 移除头部元素  如果 超出范围 会等待
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());

    }

}
