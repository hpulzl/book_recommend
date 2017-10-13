#springboot整合redis
# 一、配置环境，初步使用springbootdata
## 简单的整合方式
1. 在pom文件中添加依赖

注意spring-boot-starter-data-redis与springboot版本的问题。

我项目中使用的springboot版本是1.3.8，但是spring-boot-starter-data-redis只有1.4版本以上的。所以要指定spring-boot-starter-data-redis的version版本。

```
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.3.8.RELEASE</version>
  </parent>
```
  
```
<!--添加redis缓存依赖-->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-redis</artifactId>
      <version>1.5.0.RELEASE</version>
    </dependency>
```
2. RedisCacheConfig配置

* 使用JedisFactory建立连接工厂
* 设置一个模板RedisTemplate

```
@Configuration
@EnableAutoConfiguration
public class RedisCacheConfig extends CachingConfigurerSupport {
    
    @Value("${spring.redis.host}")
    private String host;
    
    @Value("${spring.redis.port}")
    private int port;
    
    @Value("${spring.redis.timeout}")
    private int timeout;
    
    @Value("${spring.redis.database}")
    private int database;
    
    @Value("${spring.redis.password}")
    private String password;

    /**
     * 连接redis的工厂类
     * @return
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setTimeout(timeout);
        factory.setPassword(password);
        factory.setDatabase(database);
        return factory;
    }

    /**
     * 配置StringRedisTemplate
     * @return
     */
    @Bean
    public RedisTemplate<String, String> stringRedisTemplate() {
        //StringRedisTemplate的构造方法中默认设置了stringSerializer
        /**
         * StringRedisSerializer stringSerializer = new StringRedisSerializer();
         this.setKeySerializer(stringSerializer);
         this.setValueSerializer(stringSerializer);
         this.setHashKeySerializer(stringSerializer);
         this.setHashValueSerializer(stringSerializer);
         */
        RedisTemplate<String, String> template = new StringRedisTemplate();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}

```

3. 写一个testCase

```
@Repository
public class UseRedisDao {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    private ValueOperations<String,String> valueOperations;
    private ListOperations<String,String> listOperations;

    @PostConstruct
    public void getValueOperation(){
        valueOperations = redisTemplate.opsForValue();
        listOperations = redisTemplate.opsForList();
    }

    public void setValue(String key, String value){
        valueOperations.set(key, value);
    }

    public String getValue(String key){
        return valueOperations.get(key);
    }

    public void addList(String key, String value){
        listOperations.leftPush(key, value);
    }
    public Long getListSize(String key){
        return listOperations.size(key);
    }
}

```

testCase测试部分

```
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RecommendApplication.class)
public class UseRedisTest {

    @Autowired
    private UseRedisDao useRedisDao;

    @Test
    public void setValueTest(){
        useRedisDao.setValue("sfsg", "gfdsgf");
        String value = useRedisDao.getValue("sfsg");
        System.out.println("value = " + value);
    }

    @Test
    public void setListTest(){
        useRedisDao.addList("a","ggg");
        useRedisDao.addList("a","sss");
        useRedisDao.addList("a","fdddx");
        Long size = useRedisDao.getListSize("a");
        System.out.println("size = " + size);
    }
}

```
从redis中查询插入的内容

# spring cache与redis缓存结合

****对springCache概念的了解***

> springCache的支持透明的添加缓存到应用程序，类似事务处理一般，不需要复杂的代码支持。

缓存的主要使用方式包括以下两方面
1. 缓存的声明，需要根据项目需求来妥善的应用缓存
2. 缓存的配置方式，选择需要的缓存支持，例如Ecache、redis、memercache等

## 缓存的注解介绍

> @Cacheable 触发缓存入口

> @CacheEvict 触发移除缓存

> @CacahePut 更新缓存

> @Caching 将多种缓存操作分组

> @CacheConfig 类级别的缓存注解，允许共享缓存名称

### @Cacheable

一般用于查询操作，根据key查询缓存.

1. 如果key不存在，查询db，并将结果更新到缓存中。
2. 如果key存在，直接查询缓存中的数据。

例如从sql中查询的例子。

```
@Cacheable(value = "user")
public List<User> selectAllUser(){
    return userMapper.selectAll();
}
```
Redis结果:

```

```
### @CachePut
一般用于更新和插入操作，每次都会请求db

1. 更新和插入操作，将更新或者插入的key-value存入到缓存中。


```
 /**
     * 单个user对象的插入操作，使用user+id
     * @param user
     * @return
     */
    @CachePut(key = "\"user_\" + #user.id")
    public User saveUser(User user){
        userMapper.insert(user);
        return user;
    }
    
```

Redis中的结果

```
```
### @CacheEvict

根据key删除缓存中的数据。allEntries=true表示删除缓存中的所有数据

redis中的结果

### @Caching
通过注解的属性值可以看出来，这个注解将其他注解方式融合在一起了，我们可以根据需求来自定义注解，并将前面三个注解应用在一起

```
public @interface Caching {
    Cacheable[] cacheable() default {};

    CachePut[] put() default {};

    CacheEvict[] evict() default {};
}
```





4. 了解SDR(Spring Data Redis)操作redis的API
4.1 键类型操作
其实都是基于redis提供的操作，包括string、set、list等的操作
而SDR在RedisTemplate分别提供了以下的操作。
```
ValueOperations<K, V>  针对String的操作
ListOperations<K, V> 针对list的操作
SetOperations<K, V> 针对set的操作
ZSetOperations<K, V> 针对有序的set操作
HashOperations<K, HK, HV> 针对map的操作
HyperLogLogOperations<K, V> 对HyperLogLog的操作
```
4.2 键绑定操作

```
BoundHashOperations
BoundKeyOperations
BoundListOperations
BoundSetOperations
BoundValueOperations
BoundZSetOperations


```


## 了解JedisFactory

## 了解RedisTemplate

> 该模板负责序列化和连接管理、释放连接等处理的细节。
> 线程安全的和跨多个实例可以重用。
> 大部分业务使用一个基于java的序列化器，


redisTemplate类中实现了afterPropertiesSet方法，该方法是spring初始化容器时候要回调的操作。这里默认实现了redis的序列化信息。

```
public void afterPropertiesSet(){}
```


## 了解stringRedisTemplate






### 理解RedisTemplate


# springboot主从redis的配置

## 测试redis的主从配置

一般情况下要服从一注二从三哨兵的配置方式。
所以我们需要三个redis实例，然后设置不同的端口号
配置如下所示:

### 主从配置
1. 主redis


2. 从redis_1


3. 从redis_2


主从配置验证

1. 启动
2. 写入
3. 主从同步

### 哨兵配置
1. 哨兵的性质
2. 哨兵的测试

## springboot中配置主从redis

1. application配置文件


2. RedisCacheConfig




#Redis集群主备模式部署
