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

#LinkedHashMap
继承自HashMap，保证数据插入和获取的有序性.

1. LinkedHashMap是如何维护数据的有序性的？

设计了一个双向链表，重写了newNode方法，在调用hashMap的put方法时候，该节点会持有它的prev和next引用

```
//LinkedHashMap的双向链表数据结构定义
static class Entry<K,V> extends HashMap.Node<K,V> {
        Entry<K,V> before, after;
        Entry(int hash, K key, V value, Node<K,V> next) {
            super(hash, key, value, next);
        }
    }
    //重写newNode
    Node<K,V> newNode(int hash, K key, V value, Node<K,V> e) {
        LinkedHashMap.Entry<K,V> p =
            new LinkedHashMap.Entry<K,V>(hash, key, value, e);
        //从链表的末尾进行插入
        linkNodeLast(p);
        return p;
    }
    
    private void linkNodeLast(LinkedHashMap.Entry<K,V> p) {
        LinkedHashMap.Entry<K,V> last = tail;
        tail = p;
        if (last == null)
            head = p;
        else {
            p.before = last;
            last.after = p;
        }
    }
```
这样在get的时候，就可以按照顺序去取出了。

2. LinkedHashMap实现Lru的思路

 * 设计一个双向链表，在每次put或者get的时候，将元素放到链表的末尾。每次遍历的链表的时候是从前往后开始的。
 
```
void afterNodeAccess(Node<K,V> e) { // move node to last
        LinkedHashMap.Entry<K,V> last;
        if (accessOrder && (last = tail) != e) {
            LinkedHashMap.Entry<K,V> p =
                (LinkedHashMap.Entry<K,V>)e, b = p.before, a = p.after;
            p.after = null;
            if (b == null)
                head = a;
            else
                b.after = a;
            if (a != null)
                a.before = b;
            else
                last = b;
            if (last == null)
                head = p;
            else {
                p.before = last;
                last.after = p;
            }
            tail = p;
            ++modCount;
        }
    }

```

