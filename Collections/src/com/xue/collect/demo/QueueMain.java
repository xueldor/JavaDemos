package com.xue.collect.demo;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueMain {
    private void testPriorityQueue(){
        //一种应用: 查找最大的3个元素
        Integer []nums = {2,4,6,7,1,8,11,40,25,16,14,13,18,12,3,15};
        Queue<Integer> queue = new PriorityQueue<>(3);//小根堆
        for (int n : nums) {
            if(queue.size() < 3){
                queue.add(n);
            }else {
                int top = queue.peek();
                if (top < n){
                    queue.remove();
                    queue.add(n);
                }
            }
        }
        //最后保留的一定是最大的三个元素
        System.out.println(queue.toString());

        //二 堆排序
        //先建立堆，然后移除第一个数，剩下的部分继续调整堆，这样每次都是拿出剩下中最大或者最小的元素，这样就是排序了
        Queue<Integer> queue2 = new PriorityQueue<>();//小根堆
        for (int n : nums) {
            queue2.add(n);
        }
        while (queue2.size() > 0){
            System.out.print(queue2.poll() + "    ");
        }
        System.out.println();
    }
    private void testArrayDeque(){
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(4);
        deque.addLast(5);
        deque.addFirst(6);

        //[6,4,5]
        System.out.print(deque.peekFirst() + "    ");
        System.out.print(deque.peekLast() + "    ");

        System.out.print("\n" + deque.pollFirst() + "    ");
        System.out.print(deque.pollFirst() + "    ");
        System.out.print(deque.pollFirst() + "    ");
        //如果队列为空，remove方法抛出NoSuchElementException，poll方法返回null
        System.out.print(deque.pollFirst() + "    ");
//        System.out.print(deque.removeFirst() + "    ");
    }

    public static void main(String[] args) {
        QueueMain main = new QueueMain();
        main.testPriorityQueue();

        main.testArrayDeque();
    }
}
