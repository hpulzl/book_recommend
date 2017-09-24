package com.lzl.mapper;

import com.lzl.bean.User;
import com.lzl.util.MyMapper;
import org.apache.ibatis.annotations.Delete;

/**
 * @Author: li_zhilei
 * @Date: create in 09:27 17/9/24.
 * @description:
 */
public interface UserMapper extends MyMapper<User>{

    @Delete("delete from user")
    int deleteAll();
}
