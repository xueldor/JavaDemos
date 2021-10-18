package com.xue.collect.demo;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//利用ArrayBlockingQueue实现一个简单的生产者-消费者模型
public class ArrayBlockingQueueMain {
    private static final int QUEUE_SIZE = 10;
    private static BlockingQueue<String> queue = new ArrayBlockingQueue(QUEUE_SIZE);

    public static void main(String[] args){
        //先往queue里面放数据，达到QUEUE_SIZE阻塞。i>=16时添加速度放慢，令take现场将queue取空阻塞
        Thread threadPut = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (i < 20) {
                    try {
                        queue.put("index " + i);
                        System.out.println("put index" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(i >= 16){
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    i++;
                }
            }
        });
        threadPut.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread threadTake = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (i < 20) {
                    try {
                        String take = queue.take();
                        System.out.println("take" + take);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        });
        threadTake.start();
    }
}
