package com.xue.collect.demo;

import java.util.*;

public class SetMain {
    private void testHashSet(){
        HashSet<String> hashSet = new HashSet<String>();
        //当add的元素个数比较少时，可能打印的顺序与添加一致，稍微多一些就发现无序了。
        hashSet.add("cc");
        hashSet.add("dd");
        hashSet.add("aa");
        hashSet.add("gg");
        hashSet.add("ii");
        hashSet.add("ff");
        hashSet.add("hh");
        hashSet.add("yy");
        hashSet.add("uu");
        hashSet.add("ss");
        hashSet.add("er");
        hashSet.add("oo");
        hashSet.add("pp");
        hashSet.add("pp");

        //HashSet没有索引，所以不能通过get(index)这样的方式
        //一 用迭代器
        Iterator<String> iterator = hashSet.iterator();//遍历器
        while(iterator.hasNext()){
            String el = iterator.next();
            System.out.print("    " + el);
        }
        System.out.println("\n------------------");
        //二 foreach
        for (String each : hashSet) {
            System.out.print("    " + each);
        }
        System.out.println("\n------------------");
    }

    private void testTreeSet1(){
        //String类实现了Comparable
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("dd");
        treeSet.add("ee");
        treeSet.add("aa");
        treeSet.add("hh");
        treeSet.add("ff");
        treeSet.add("ff");
        for (String each : treeSet) {
            System.out.print("    " + each);
        }
        System.out.println("\n------------------");

        //通过Comparator让顺序倒过来
        treeSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //令dd==ee，于是插入ee时，treeset认为是同一个元素，没有加进来
                if(o1.equals("dd") && o2.equals("ee")) return 0;
                if(o1.equals("ee") && o2.equals("dd")) return 0;
                return o2.compareTo(o1);
            }
        });
        treeSet.add("dd");
        treeSet.add("ee");
        treeSet.add("aa");
        treeSet.add("hh");
        treeSet.add("ff");
        treeSet.add("ff");
        for (String each : treeSet) {
            System.out.print("    " + each);
        }
    }

    public static void main(String[] args) {
        SetMain main = new SetMain();
        main.testHashSet();
        main.testTreeSet1();
    }
}
