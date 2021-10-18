### ArrayList  

![ArrayList remove](img/Arraylist_remove.png)
参阅上图，数组和ArrayList的缺陷-> 删除一个元素要付出很大的代价-> 被删除元素之后的所有元素都要向数组的前端移动
->只想删除却拷贝了一次 -> 插入一个元素也是如此。  

List需要注意: 删除-》 数据向前移动 -》 索引变化 -》 不应该在for里面删除 -》 用迭代器移除元素