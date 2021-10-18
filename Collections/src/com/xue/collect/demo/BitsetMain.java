package com.xue.collect.demo;

import java.util.BitSet;

public class BitsetMain {
    public static void main(String[] args) {
        BitSet bitSet = new BitSet(15);
        System.out.println(bitSet.get(100));
        bitSet.set(2000);//Bitset内部是有自动扩容的
        System.out.println(bitSet.get(2000));

        bitSet.set(0);//第0位设置为开(true)
        System.out.println(bitSet.get(0));
        bitSet.clear(0);//第0位设置为关
        System.out.println(bitSet.get(0));

        System.out.println("length=" + bitSet.length());//2001,因为前面set(2000)，导致长度扩到了2001

        BitSet bitSet1 = new BitSet(16);bitSet1.set(1);
        BitSet bitSet2 = new BitSet(16);bitSet2.set(3);
        System.out.println(bitSet1);//{1}
        System.out.println(bitSet2);//{3}
        bitSet1.and(bitSet2);//与操作。改变是 bitSet1 ， bitSet2不变
        System.out.println(bitSet1);//00010 与上 01000 结果为 0000 ，故输出{}
        System.out.println(bitSet2);//{3}
        System.out.println("-----------");

        bitSet1.clear();bitSet1.set(1);
        bitSet2.clear();bitSet2.set(3);
        bitSet1.or(bitSet2);//或
        System.out.println(bitSet1);//output {1, 3}
        System.out.println(bitSet2);//{3}

        bitSet2.flip(0);//反转为true
        bitSet2.flip(3);//反转false
        System.out.println(bitSet2);//{0}

    }
}
