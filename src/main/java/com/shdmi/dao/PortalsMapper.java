package com.shdmi.dao;

import com.shdmi.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by shanghai on 2017/10/25.
 */
public interface PortalsMapper {

    int add(Client client);

    int count();

    List<Client> findList(@Param("index") int index, @Param("pageSize") int pageSize);

    List<Works> selectworks();

    List<Works> selecthomepageworks();

    Works selectwork(@Param("id") int id);

    List<WorksPicture> selectworkspic(@Param("workcaseId") int workcaseId);

    List<WorksPicture> selectworkscoverpic(@Param("workcaseId") int workcaseId);

    List<SinglePic> selectPicByType(@Param("type") int type);

    List<Industryinformation> selectIndustryinformations(@Param("size") int size);

    Industryinformation selectIndustryinformationById(@Param("id") int id);

    List<Industryinformation> selectPicByInformationId(@Param("industryinformationId") int industryinformationId);
}
