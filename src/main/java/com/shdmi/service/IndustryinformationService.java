package com.shdmi.service;

import com.shdmi.dao.IndustryinformationMapper;
import com.shdmi.dao.WorksMapper;
import com.shdmi.entity.Industryinformation;
import com.shdmi.entity.Works;
import com.shdmi.entity.WorksPicture;
import com.shdmi.utils.CacheUtil;
import com.shdmi.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by user on 2017/11/19.
 */
@Service
public class IndustryinformationService {

    private static final String CACHE_KEY = "industryinformations_cache_key_%d";
    @Autowired
    private IndustryinformationMapper industryinformationMapper;
    @Autowired
    private CacheUtil cacheUtil;
    private String cacheKey(int projectId) {
        return String.format(CACHE_KEY, projectId);
    }

    public int add(Industryinformation industryinformation) {
        return industryinformationMapper.add(industryinformation);
    }

    public int update(Industryinformation industryinformation) {
        int line = industryinformationMapper.update(industryinformation);
        return line;
    }

    public int delete(int id) {
        return industryinformationMapper.delete(id);
    }

    public Page<Industryinformation> findByPage(int pageNumber, int pageSize) {
        int totalCount = industryinformationMapper.count();
        Page<Industryinformation> page = new Page<>();
        int index = 0;
        if (pageNumber != 0 && pageSize != 0) index = (pageNumber - 1) * pageSize;
        List<Industryinformation> industryinformations = industryinformationMapper.findList(index, pageSize);
        if (pageNumber != 0 && pageSize != 0){
            page.setPageNo(pageNumber);
            page.setPageSize(pageSize);
        }
        page.setTotalCount(totalCount);
        page.setResult(industryinformations);
        return page;
    }

    @Transactional(readOnly = true)
    public Industryinformation seleceIndustryinformationById(int id) {
        Industryinformation industryinformation = industryinformationMapper.seleceIndustryinformationById(id);
        return industryinformation;
    }

    public int picAdd(Industryinformation industryinformation) {
        return industryinformationMapper.picadd(industryinformation);
    }

    public int deletePic(int id){
        return industryinformationMapper.deletePic(id);
    }

    public Page<Industryinformation> findPicByPage(int industryinformationId, int pageNumber, int pageSize) {
        int totalCount = industryinformationMapper.piccount(industryinformationId);
        Page<Industryinformation> page = new Page<>();
        int index = 0;
        if (pageNumber != 0 && pageSize != 0) index = (pageNumber - 1) * pageSize;
        List<Industryinformation> industryinformations = industryinformationMapper.picfindList(industryinformationId, index, pageSize);
        if (pageNumber != 0 && pageSize != 0){
            page.setPageNo(pageNumber);
            page.setPageSize(pageSize);
        }
        page.setTotalCount(totalCount);
        page.setResult(industryinformations);
        return page;
    }

    public List<Industryinformation> selecePicById(int id) {
        return industryinformationMapper.selecePicById(id);
    }

    public List<Industryinformation> selecePicByIndustryinformationId(int industryinformationId) {
        return industryinformationMapper.selecePicByIndustryinformationId(industryinformationId);
    }
}
