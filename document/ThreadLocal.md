#ThreadLocal
指的是线程的局部变量，每个线程使用ThreadLocal设置的值，只能在当前线程中可见。
对外提供的public方法包括
1. set
2. get
3. remove

```
public class ThreadLocalDemo {
    private static ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        //main thread set threadLocal
        stringThreadLocal.set("hello");
        stringThreadLocal.set("aaaaa");
        stringThreadLocal.set("bbbbb");
        System.out.println(Thread.currentThread().getName()+" get " + stringThreadLocal.get());
        //t1 thread set threadLocal
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                stringThreadLocal.set("hhhh");
                System.out.println(Thread.currentThread().getName()+" get " + stringThreadLocal.get());
            }
        });
        t1.start();

    }

}
```
结果

```
main get bbbbb
Thread-0 get hhhh
```
ThreadLocal保证不同线程的数据相互独立。

##应用场景
对ThreaLocal的理解很简单，但是究竟是什么场景下运用这个对象呢？
可以用于多线程情形下的数据库连接池，或者session管理中。
一般使用private static final来标识。

## 源码分析
与ThreadLocal相关联的类包括Thread和其内部类ThreadLocalMap
ThreadLocal的set和get方法其实主要的实现逻辑还是存在于ThreadLocalMap中的。

####创建ThreadLocal

``` 
private static ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
```
这个构造方法中没有任何实现

```
public ThreadLocal() {
    }
```

#### set方法解析

ThreadLocalMap的set方法扩容以及hash冲突解决方案

* 扩容：当index >= size * 3/4时进行扩容。扩大为当前table的两倍容量
* hash冲突: 计算hash使用散列法（hash & (size -1 )）并且会在每次set的时候清理陈旧(stale)的值。
* 移除旧值: 在set的时候会根据容器大小和key值是否为空清理旧值。

```
public void set(T value) {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
    }
```

`ThreadLocalMap`中的set方法

```
 private void set(ThreadLocal<?> key, Object value) {

            Entry[] tab = table;
            int len = tab.length;
            //使用散列hash算法随机生成一个数组的位置
            int i = key.threadLocalHashCode & (len-1);
			//遍历entry中的值
            for (Entry e = tab[i];
                 e != null;
                 e = tab[i = nextIndex(i, len)]) {
                //获取key值
                ThreadLocal<?> k = e.get();
					
					//如果key存在，且不为空，就直接覆盖陈旧(stale)的value。
                if (k == key) {
                    e.value = value;
                    return;
                }
					//如果key为空，需要清理并替换key、value值
                if (k == null) {
                    replaceStaleEntry(key, value, i);
                    return;
                }
            }
				//Entry为空，创建新的Entry并set值
            tab[i] = new Entry(key, value);
            int sz = ++size;

            if (!cleanSomeSlots(i, sz) && sz >= threshold)
           //如果没有清理陈旧的值，并且size大于数组大小的2/3执行该方法
                rehash();
        }
        
```

#### get方法解析

hash散列算法获取key值的索引。如果Entry在table[i]中直接返回Entry对象。不存在执行getEntryAfterMiss()方法。

```
private Entry getEntry(ThreadLocal<?> key) {
            int i = key.threadLocalHashCode & (table.length - 1);
            Entry e = table[i];
            //如果存在key，直接返回对应的value
            if (e != null && e.get() == key)
                return e;
            else
                return getEntryAfterMiss(key, i, e);
        }
```
循环遍历table数组，结束条件分两种。
1. 找到Entry对象
2. table中为空，结束while循环

```
private Entry getEntryAfterMiss(ThreadLocal<?> key, int i, Entry e) {
            Entry[] tab = table;
            int len = tab.length;

            while (e != null) {
                ThreadLocal<?> k = e.get();
                if (k == key)
                    return e;
                if (k == null)
                    expungeStaleEntry(i);
                else
                    i = nextIndex(i, len);
                e = tab[i];
            }
            return null;
        }
```

#### remove分析

1. 获取当前ThreadLocalMap如果map为空就不做任何操作
2. map不为空执行remove方法s
```
public void remove() {
         ThreadLocalMap m = getMap(Thread.currentThread());
         if (m != null)
             m.remove(this);
     }
```
1. 此处将Entry指向的对象key设置为null

```
//循环中只执行一次，如果存在key将其清空。
 private void remove(ThreadLocal<?> key) {
            Entry[] tab = table;
            int len = tab.length;
            int i = key.threadLocalHashCode & (len-1);
            for (Entry e = tab[i];
                 e != null;
                 e = tab[i = nextIndex(i, len)]) {
                if (e.get() == key) {
                    e.clear();
                    expungeStaleEntry(i);
                    return;
                }
            }
        }
        
 //此处将Entry指向的对象key设置为null  
 public void clear() {
        this.referent = null;
    }
```