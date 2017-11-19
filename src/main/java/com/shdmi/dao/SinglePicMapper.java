package com.shdmi.dao;

import com.shdmi.entity.SinglePic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by user on 2017/11/18.
 */
public interface SinglePicMapper {

    int add(SinglePic singlePic);

    int delete(int id);

    int count();

    List<SinglePic> selecePic(@Param("index") int index, @Param("pageSize") int pageSize);

    int count(@Param("type") Byte type);

    List<SinglePic> selecePicByType(@Param("type") Byte type, @Param("index") int index, @Param("pageSize") int pageSize);

    SinglePic selecePicById(@Param("id") int id);
}
