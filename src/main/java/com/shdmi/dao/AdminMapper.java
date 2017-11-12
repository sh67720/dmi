package com.shdmi.dao;

import com.shdmi.entity.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * Created by vincent on 16/3/20.
 */
public interface AdminMapper {
    Admin login(@Param("username") String username, @Param("password") String password);
}
