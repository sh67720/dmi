package com.shdmi.dao;

import com.shdmi.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by shanghai on 2017/10/29.
 */
public interface WorksMapper {

    //案例列表
    int add(Works works);

    int count();

    int update(Works works);

    Works get(int id);

    List<Works> findList(@Param("index") int index, @Param("pageSize") int pageSize);

    int delete(int id);

    //案例图片列表
    int picadd(WorksPicture worksPicture);

    int piccount(@Param("workcaseId") int workcaseId);

    List<WorksPicture> picfindList(@Param("workcaseId") int workcaseId, @Param("index") int index, @Param("pageSize") int pageSize);

    int picdelete(int id);

    List<WorksPicture> selecePicId(@Param("id") int id);

    List<WorksPicture> selecePicWorkcaseId(@Param("workcaseId") int workcaseId);
}
