package com.shdmi.dao;

import com.shdmi.entity.Industryinformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by user on 2017/11/19.
 */
public interface IndustryinformationMapper {

    //案例列表
    int add(Industryinformation industryinformation);

    int update(Industryinformation industryinformation);

    int delete(int id);

    int count();

    List<Industryinformation> findList(@Param("index") int index, @Param("pageSize") int pageSize);

    Industryinformation seleceIndustryinformationById(int id);

    //案例图片列表
    int picadd(Industryinformation industryinformation);

    int deletePic(@Param("id") int id);

    int piccount(@Param("industryinformationId") int industryinformationId);

    List<Industryinformation> picfindList(@Param("industryinformationId") int industryinformationId, @Param("index") int index, @Param("pageSize") int pageSize);

    List<Industryinformation> selecePicById(@Param("id") int id);

    List<Industryinformation> selecePicByIndustryinformationId(@Param("industryinformationId") int industryinformationId);
}
