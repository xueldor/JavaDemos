package com.xue.collect.demo;

import java.util.*;

//由于ArrayList和LinkedList已经非常熟悉，故demo简单一点
public class ListMain {

    private void testArrayList(){
        List<String> list = new ArrayList<>(10);
        list.add("aa");
        list.add("");
        list.add(null);
        list.add(null);
        list.add("cc");
        list.add("aa");
        System.out.println(list.size());

        list.remove(null);//remove first null
        System.out.println(list.size());
    }
    private void testLinkedList(){
        List<String> list = new LinkedList<>();
        list.add("aa");
        list.add("");
        list.add(null);
        list.add(null);
        list.add("cc");
        list.add(3,"aa");
        list.add("dd");
        System.out.println(list.size());

        list.remove(null);//remove first null
        System.out.println(list.size());

        //remove会改变元素的索引，所以不能在循环里remove
        //会抛出ConcurrentModificationException
//        for (String s:list) {
//            if(s == null){
//                list.remove(null);
//            }
//        }

        //remove会改变元素的索引，所以不能在循环里remove
        for(int i = 0;i<list.size();i++){
            System.out.println("before : " + list.get(i));
            if(list.get(i) == null){
                list.remove(i);
                i--;//如果非要在for循环里调用remove的话，需要手动处理i。
            }
            System.out.println("after : " + list.get(i));
        }

        //这才是标准方法
        Iterator<String> iter = list.iterator();
        while(iter.hasNext()){
            String next = iter.next();//每次调用next()方法都应该先判断hasNext()
            System.out.println(/* wrong : iter.next()*/ next);
            if(next == null){
                iter.remove();
                System.out.println("prefer null : " + next);
            }
        }

        //ListIterator可以向前移动
        ListIterator<String> liter = list.listIterator();
        if(liter.hasPrevious()) {
            System.out.println(liter.previous());
        }

    }

    public static void main(String[] args) {
        ListMain main = new ListMain();
        main.testArrayList();
        main.testLinkedList();
    }
}
