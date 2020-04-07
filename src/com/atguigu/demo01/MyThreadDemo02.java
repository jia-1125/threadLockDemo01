package com.atguigu.demo01;

import java.util.concurrent.CopyOnWriteArrayList;

class Shop{
    private int number=0;
    public void increment() throws  Exception{
        if (number!=0){
            this.wait();
        }
        number--;
    }
    public void decrement() throws  Exception{

    }
}

public class MyThreadDemo02 {
    public static void main(String[] args) {

    }
}
