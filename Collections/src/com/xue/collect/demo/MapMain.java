package com.xue.collect.demo;

import java.time.DayOfWeek;
import java.util.*;

public class MapMain {

    private void testHashMap1(){
        //判断key是否相等，是这样判断的：
        // 要么1、key对象地址相同，即key1和key2是同一个对象
        // 要么2、hashCode相等并且keyA.equals(keyB)
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("a", "2");
        map.put("a", "3");
        System.out.println(map.size()); //1,容易理解

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(new String("a"), "1");
        hashMap.put(new String("a"), "2");
        hashMap.put(new String("a"), "3");
        System.out.println(hashMap.size()); //1，虽然是不同的对象，但是hashCode相等

        Map<Integer, String> hashMap2 = new HashMap<>();
        hashMap2.put(new Integer(200), "1");
        hashMap2.put(new Integer(200), "2");
        hashMap2.put(new Integer(200), "3");
        System.out.println(hashMap2.size()); //1,hashCode相等

        Map<Object, String> hashMap3 = new HashMap<>();
        hashMap3.put(new Object(), "1");
        hashMap3.put(new Object(), "2");
        hashMap3.put(new Object(), "3");
        System.out.println(hashMap3.size()); //3,hashCode各不相同
    }

    private void testIdentityHashMap(){
        //IdentityHashMap比较key时直接用==，也就是比较地址
        Map<String, String> map = new IdentityHashMap<>();
        map.put("a", "1");
        map.put("a", "2");
        map.put("a", "3");
        System.out.println(map.size()); //1,容易理解

        Map<String, String> identityHashMap = new IdentityHashMap<>();
        identityHashMap.put(new String("a"), "1");
        identityHashMap.put(new String("a"), "2");
        identityHashMap.put(new String("a"), "3");
        System.out.println(identityHashMap.size()); //3
    }

    WeakHashMap map = new WeakHashMap();
    private void testWeakHashMap1(){
        Object o1 = new Object();
        Object o2 = new Object();
        map.put(o1,"aa");//自动装箱成Integer
        map.put(o2,"bb");
        //然后接着执行testWeakHashMap2
    }
    void testWeakHashMap2(){;
        System.gc();//触发垃圾回收
        System.out.println(map);//我们发现map已经变成空的了
    }


    void testLinkedHashMap(){
        // 第三个参数用于指定accessOrder值
        //false表示按插入顺序存储的。这里指定为true，验证每次调用get，会导致对象移动到末尾
        Map<String, String> linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);
        linkedHashMap.put("name1", "josan1");
        linkedHashMap.put("name2", "josan2");
        linkedHashMap.put("name3", "josan3");
        System.out.println("开始时顺序：");
        Set<Map.Entry<String, String>> set = linkedHashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println("key:" + key + ",value:" + value);
        }
        System.out.println("通过get方法，导致key为name1对应的Entry到表尾，然后put方法更新value，导致name2到末尾");
        linkedHashMap.get("name1");
        linkedHashMap.put("name2", "josan2-2");
        Set<Map.Entry<String, String>> set2 = linkedHashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator2 = set2.iterator();
        while(iterator2.hasNext()) {
            Map.Entry entry = iterator2.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println("key:" + key + ",value:" + value);
        }
    }
    void testLinkedHashMap2(){
        // 第三个参数用于指定accessOrder值
        //false表示按插入顺序存储的。读取、更新不会改变顺序
        Map<String, String> linkedHashMap = new LinkedHashMap<>(16, 0.75f, false);
        linkedHashMap.put("name1", "josan1");
        linkedHashMap.put("name2", "josan2");
        linkedHashMap.put("name3", "josan3");
        System.out.println("开始时顺序：");
        Set<Map.Entry<String, String>> set = linkedHashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println("key:" + key + ",value:" + value);
        }
        System.out.println("调用get/put方法，观察顺序是否会变");
        linkedHashMap.get("name1");
        linkedHashMap.put("name2", "josan2-2");
        Set<Map.Entry<String, String>> set2 = linkedHashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator2 = set2.iterator();
        while(iterator2.hasNext()) {
            Map.Entry entry = iterator2.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println("key:" + key + ",value:" + value);
        }
    }

    private void testEnumMap(){
        //当然，完全可以用HashMap实现。但是 EnumMap 内部以一个非常紧凑的数组存储value,不但效率最高，而且没有额外的空间浪费
        Map<DayOfWeek, String> map = new EnumMap<>(DayOfWeek.class);
        map.put(DayOfWeek.MONDAY, "星期一");
        map.put(DayOfWeek.TUESDAY, "星期二");
        map.put(DayOfWeek.WEDNESDAY, "星期三");
        map.put(DayOfWeek.THURSDAY, "星期四");
        map.put(DayOfWeek.FRIDAY, "星期五");
        map.put(DayOfWeek.SATURDAY, "星期六");
        map.put(DayOfWeek.SUNDAY, "星期日");
        System.out.println(map);
        System.out.println(map.get(DayOfWeek.MONDAY));
    }

    public static void main(String[] args) {
        MapMain main = new MapMain();
        main.testHashMap1();
        System.out.println("-------");
        main.testIdentityHashMap();

        main.testWeakHashMap1();//执行结束后，o1和o2的引用作为局部变量会被释放。如果用HashMap，由于映射的存在，堆里的对象依然有变量引用它。
        //而使用WeakHashMap的话，这样引用是“弱”的。
        main.testWeakHashMap2();


        main.testLinkedHashMap();
        main.testLinkedHashMap2();

        main.testEnumMap();
    }
}
