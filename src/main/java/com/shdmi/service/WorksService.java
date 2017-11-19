package com.shdmi.service;

import com.shdmi.dao.WorksMapper;
import com.shdmi.entity.Works;
import com.shdmi.entity.WorksPicture;
import com.shdmi.utils.CacheUtil;
import com.shdmi.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by shanghai on 2017/10/29.
 */
@Service
public class WorksService {

    private static final String CACHE_KEY = "works_cache_key_%d";
    @Autowired
    private WorksMapper worksMapper;
    @Autowired
    private CacheUtil cacheUtil;
    private String cacheKey(int projectId) {
        return String.format(CACHE_KEY, projectId);
    }

    public int add(Works works) {
        return worksMapper.add(works);
    }

    public int update(Works works) {
        int line = worksMapper.update(works);
        /*int worksId = works.getId();
        Works db = getFromDB(worksId);
        db.setName(works.getName().replace("\n","<br>"));
        db.setServiceContent(works.getServiceContent().replace("\n","<br>"));
        db.setBrandIntroduction(works.getBrandIntroduction().replace("\n","<br>"));
        db.setRemark(works.getRemark().replace("\n","<br>"));
        cacheUtil.put(cacheKey(worksId), db);*/
        return line;
    }

    @Transactional(readOnly = true)
    public Works getFromDB(int id) {
        Works works = worksMapper.get(id);
        return works;
    }

    public Page<Works> findByPage(int pageNumber, int pageSize) {
        int totalCount = worksMapper.count();
        Page<Works> page = new Page<>();
        int index = 0;
        if (pageNumber != 0 && pageSize != 0) index = (pageNumber - 1) * pageSize;
        List<Works> works = worksMapper.findList(index, pageSize);
        if (pageNumber != 0 && pageSize != 0){
            page.setPageNo(pageNumber);
            page.setPageSize(pageSize);
        }
        page.setTotalCount(totalCount);
        page.setResult(works);
        return page;
    }

    public int delete(int id) {
        return worksMapper.delete(id);
    }

    public int picAdd(WorksPicture worksPicture) {
        return worksMapper.picadd(worksPicture);
    }

    public Page<WorksPicture> findPicByPage(int workcaseId, int pageNumber, int pageSize) {
        int totalCount = worksMapper.piccount(workcaseId);
        Page<WorksPicture> page = new Page<>();
        int index = 0;
        if (pageNumber != 0 && pageSize != 0) index = (pageNumber - 1) * pageSize;
        List<WorksPicture> worksPictures = worksMapper.picfindList(workcaseId, index, pageSize);
        if (pageNumber != 0 && pageSize != 0){
            page.setPageNo(pageNumber);
            page.setPageSize(pageSize);
        }
        page.setTotalCount(totalCount);
        page.setResult(worksPictures);
        return page;
    }

    public int picDelete(int id){
        return worksMapper.picdelete(id);
    }

    public List<WorksPicture> selecePicId(int workcaseId) {
        return worksMapper.selecePicId(workcaseId);
    }

    public List<WorksPicture> selecePicWorkcaseId(int workcaseId) {
        return worksMapper.selecePicWorkcaseId(workcaseId);
    }
}
