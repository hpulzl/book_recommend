package com.lzl.cache;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;

import java.lang.annotation.*;

/**
 * @Author: li_zhilei
 * @Date: create in 18:23 17/9/24.
 * @description:
 */
@Caching(
        put = {
                @CachePut(value = "user", key = "\"user_\" + #user.id"),
                @CachePut(value = "user", key = "#user.name"),
                @CachePut(value = "user", key = "#user.account")
        }
)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SaveUserInfo {

}
