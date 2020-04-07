package com.atguigu.demo01;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class MyThreadConcurrentDemo01 {
    public static void main(String[] args) {
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
//        List<String> list=new CopyOnWriteArrayList<>();//ArrayList
//        Set<String> set=new CopyOnWriteArraySet<>();//hashSet
        Map<String,String>map=new ConcurrentHashMap<>();//HashMap
        for (int i = 1; i <= 3000; i++) {
            new Thread(() -> {
//                list.add(UUID.randomUUID().toString().substring(0, 8));
//                set.add(UUID.randomUUID().toString().substring(0, 8));
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
