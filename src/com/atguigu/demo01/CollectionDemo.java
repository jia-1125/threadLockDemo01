package com.atguigu.demo01;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

//list set map 集合 解决并发不安全问题
public class CollectionDemo {
    public static void main(String[] args) {
        //第一种方案：Collections.synchrnized集合名称（list或Set 或map）
        List<String> list1=Collections.synchronizedList(new ArrayList<>());
        //第二种方案 new CopyOnWriteArrayList
        List<String>list=new CopyOnWriteArrayList<>();//使用CopyOnWriteArrayList 解决 ArrayList并发写出现的 ConcurrentModificationException异常(
        // ArrayList 不是线程安全的 )
//        for (int i = 1; i <=3000 ; i++) {
//            new Thread(
//                    ()->{
//                        list.add(UUID.randomUUID().toString().substring(0, 8));
//                        System.out.println(list);
//                    }
//                    ,String.valueOf(i)).start();
//        }

        //set集合不安全解决方案
        Set<String> set=new CopyOnWriteArraySet<>();//使用CopyOnWriteArraySet解决HashSet 并发写出现的ConcurrentModificationException 异常
        //HashSet底层 HashMap  key-value value是常量 Object
//        for (int i = 1; i <=3000 ; i++) {
//            new Thread(
//                    ()->{
//                        set.add(UUID.randomUUID().toString().substring(0, 8));
//                        System.out.println(set);
//                    }
//                    ,String.valueOf(i)).start();
//        }
        //map集合不安全解决方案
        Map<String,String>map=new ConcurrentHashMap<String,String>();//使用ConcurrentHashMap解决 HashMap并发写出现的 ConcurrentModificationException异常
        for (int i = 1; i <=300 ; i++) {
          new Thread(
                  ()->{
                      map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                      System.out.println(map);
                  }
          ).start();
        }
    }
}
