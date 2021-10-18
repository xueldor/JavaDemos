package com.xue.collect.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.shuffle;

public class CollectionsMain {
    private void sort(){
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 49; i++)
            numbers.add(i );
        System.out.println(numbers);
        Collections.shuffle(numbers);//此方法将顺序随机打乱
        System.out.println(numbers);

        List subList = numbers.subList(5,15);
        Collections.sort(subList);////此方法将list排序。可以传入Comparator
        System.out.println(subList);
    }


    private void search(){
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 49; i++)
            numbers.add(i * 2 );
        //二分搜索
        int index = Collections.binarySearch(numbers,30);//参数list必须是已经排好序的
        System.out.println("index " + index + "," + numbers.get(index));

        //如果不存在，返回负数，并且 -pos -1 即为应该插入的位置
        int pos = Collections.binarySearch(numbers,39);
        System.out.println(pos);
        numbers.add(-pos-1,70);

        //numbers: 2,4,6,8,10...
        int pos2 = Collections.binarySearch(numbers,1);
        System.out.println(pos2);
        int pos3 = Collections.binarySearch(numbers,3);
        System.out.println(pos3);
    }

    public static void main(String[] args) {
        CollectionsMain main = new CollectionsMain();
        main.sort();

        main.search();
    }
}
