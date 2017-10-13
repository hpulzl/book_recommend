#HashMap
HashMap主要要掌握的知识点
1. Put方法的逻辑
2. get方法的逻辑
3. 容器扩容的逻辑
4. 红黑树
5. 链表
##参数介绍


```
/**
    默认的容器大小
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    /**
	容器最大容量
    **/
    static final int MAXIMUM_CAPACITY = 1 << 30;
    /**
     红黑树转链表的阈值   
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    static final int TREEIFY_THRESHOLD = 8;

    /**
    红黑树转链表的阈值
     */
    static final int UNTREEIFY_THRESHOLD = 6;
```
put方法的逻辑
1. 计算hash值，从table中取出N