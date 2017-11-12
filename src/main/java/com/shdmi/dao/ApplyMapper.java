package com.shdmi.dao;

import com.shdmi.entity.Apply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by vincent on 16/3/23.
 */
public interface ApplyMapper {

    int count(@Param("id") int id);

    List<Apply> findList(@Param("id") int projectId, @Param("index") int index, @Param("pageSize") int pageSize);

    int add(Apply apply);

    int delete(int projectId);

    int exist(String phone);
}
