package com.shdmi.dao;

import com.shdmi.entity.Apply;
import com.shdmi.entity.Client;
import com.shdmi.entity.Works;
import com.shdmi.entity.WorksPicture;
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
}
