package com.shdmi.service;

import com.shdmi.dao.PortalsMapper;
import com.shdmi.entity.*;
import com.shdmi.utils.Page;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shanghai on 2017/10/25.
 */
@Service
public class PortalsService {

    @Autowired
    private PortalsMapper portalsMapper;
    public int add(Client client) {
        return portalsMapper.add(client);
    }

    public Page<Client> findByPage(int pageNumber, int pageSize) {
        int totalCount = portalsMapper.count();
        Page<Client> page = new Page<>();
        int index = 0;
        if (pageNumber != 0 && pageSize != 0) index = (pageNumber - 1) * pageSize;
        List<Client> clients = portalsMapper.findList(index, pageSize);
        if (pageNumber != 0 && pageSize != 0){
            page.setPageNo(pageNumber);
            page.setPageSize(pageSize);
        }
        page.setTotalCount(totalCount);
        page.setResult(clients);
        return page;
    }

    public List<Works> selectWorks() {
        List<Works> works = portalsMapper.selectworks();
        return works;
    }

    public List<Works> selectHomepageWorks() {
        List<Works> works = portalsMapper.selecthomepageworks();
        return works;
    }

    public Works selectWork(int id) {
        Works work = portalsMapper.selectwork(id);
        return work;
    }

    public List<WorksPicture> selectWorkspic(int workcaseId) {
        List<WorksPicture> worksPictures = portalsMapper.selectworkspic(workcaseId);
        return worksPictures;
    }

    public List<WorksPicture> selectWorksCoverpic(int workcaseId) {
        List<WorksPicture> worksPictures = portalsMapper.selectworkscoverpic(workcaseId);
        return worksPictures;
    }

    public List<SinglePic> selectPicByType(int type) {
        List<SinglePic> singlePics = portalsMapper.selectPicByType(type);
        return singlePics;
    }

    public List<Industryinformation> selectIndustryinformations(int size) {
        List<Industryinformation> industryinformations = portalsMapper.selectIndustryinformations(size);
        return industryinformations;
    }

    public Industryinformation selectIndustryinformationById(int id) {
        Industryinformation industryinformation = portalsMapper.selectIndustryinformationById(id);
        return industryinformation;
    }

    public List<Industryinformation> selectPicByInformationId(int industryinformationId) {
        List<Industryinformation> industryinformations = portalsMapper.selectPicByInformationId(industryinformationId);
        return industryinformations;
    }
}
