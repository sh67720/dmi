package com.shdmi.service;

import com.shdmi.dao.ApplyMapper;
import com.shdmi.dao.ProjectMapper;
import com.shdmi.entity.Apply;
import com.shdmi.entity.Project;
import com.shdmi.utils.Page;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vincent on 16/3/23.
 */
@Service
public class ApplyService {

    @Autowired
    private ApplyMapper applyMapper;
    @Autowired
    private ProjectMapper projectMapper;

    public Page<Apply> findByPage(int projectId, int pageNumber, int pageSize) {
        int totalCount = applyMapper.count(projectId);
        Page<Apply> page = new Page<>();
        int index = 0;
        if (pageNumber != 0 && pageSize != 0) index = (pageNumber - 1) * pageSize;
        List<Apply> applies = applyMapper.findList(projectId, index, pageSize);
        if (CollectionUtils.isNotEmpty(applies)) {
            for (Apply apply : applies) {
                Project project = projectMapper.get(apply.getProjectId());
                String title = project.getTitle();
                String subTitle = project.getSubTitle();
                apply.setTitle(title);
                apply.setSubTitle(subTitle);
            }
        }
        if (pageNumber != 0 && pageSize != 0){
            page.setPageNo(pageNumber);
            page.setPageSize(pageSize);
        }
        page.setTotalCount(totalCount);
        page.setResult(applies);
        return page;
    }

    public int add(Apply apply) {
        return applyMapper.add(apply);
    }

    public int exist(String phone) {
        return applyMapper.exist(phone);
    }
}
