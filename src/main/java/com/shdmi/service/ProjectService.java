package com.shdmi.service;

import com.shdmi.dao.ApplyMapper;
import com.shdmi.dao.ProjectMapper;
import com.shdmi.entity.Picture;
import com.shdmi.entity.Project;
import com.shdmi.entity.Tag;
import com.shdmi.utils.CacheUtil;
import com.shdmi.utils.Page;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vincent on 16/3/20.
 */
@Service
@Transactional
public class ProjectService {
    private static final String CACHE_KEY = "project_cache_key_%d";
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ApplyMapper applyMapper;
    @Autowired
    private CacheUtil cacheUtil;

    private String cacheKey(int projectId) {
        return String.format(CACHE_KEY, projectId);
    }

    @Transactional(readOnly = true)
    public Page<Project> findByPage(String title, int pageNumber, int pageSize){
        int totalCount = projectMapper.count(title);
        Page<Project> page = new Page<>();
        int index = 0;
        if (pageNumber != 0 && pageSize != 0) index = (pageNumber - 1) * pageSize;
        List<Project> projects = projectMapper.findList(title, index, pageSize);
        if (pageNumber != 0 && pageSize != 0){
            page.setPageNo(pageNumber);
            page.setPageSize(pageSize);
        }
        page.setTotalCount(totalCount);
        page.setResult(projects);
        return page;
    }

    @Transactional(readOnly = true)
    public Project get(int id) {
        Project project = cacheUtil.get(cacheKey(id), Project.class);
        if (project == null) {
            project = getFromDB(id);
            project.setPreferential(project.getPreferential().replace("\n","<br>"));
            project.setNews(project.getNews().replace("\n","<br>"));
            project.setRemark(project.getRemark().replace("\n","<br>"));
            project.setTraffic(project.getTraffic().replace("\n","<br>"));
            project.setSupporting(project.getSupporting().replace("\n","<br>"));
            cacheUtil.put(cacheKey(id), project);
        }
        return project;
    }

    @Transactional(readOnly = true)
    public Project getFromDB(int id) {
        Project project = projectMapper.get(id);
        String[] tp = projectMapper.findPictures(project.getId(), (byte) 0);
        project.setTopPictures(tp);
        String[] bp = projectMapper.findPictures(project.getId(), (byte) 1);
        project.setBottomPictures(bp);
        String[] tt = projectMapper.findTags(project.getId(), (byte) 0);
        project.setTopTags(tt);
        String[] bt = projectMapper.findTags(project.getId(), (byte) 1);
        project.setBottomTags(bt);
        return project;
    }

    public int update(Project project) {
        String[] tp = project.getTopPictures();
        String[] bp = project.getBottomPictures();
        String[] tt = project.getTopTags();
        int[] tts = project.getTopTagSorts();
        String[] bt = project.getBottomTags();
        int[] bts = project.getBottomTagSorts();
        int line = projectMapper.update(project);
        int projectId = project.getId();
        if (!ArrayUtils.isEmpty(tp)) {
            delPicture(projectId, (byte) 0);
            addPictures(tp, projectId, (byte) 0);
        }
        if (!ArrayUtils.isEmpty(bp)) {
            delPicture(projectId, (byte) 1);
            addPictures(bp, projectId, (byte) 1);
        }
        delTag(projectId, (byte) 0);
        addTags(tt, tts, projectId, (byte) 0);
        delTag(projectId, (byte) 1);
        addTags(bt, bts, projectId, (byte) 1);
        Project db = getFromDB(projectId);
        db.setPreferential(project.getPreferential().replace("\n","<br>"));
        db.setNews(project.getNews().replace("\n","<br>"));
        db.setRemark(project.getRemark().replace("\n","<br>"));
        db.setTraffic(project.getTraffic().replace("\n","<br>"));
        db.setSupporting(project.getSupporting().replace("\n","<br>"));
        cacheUtil.put(cacheKey(projectId), db);
        return line;
    }

    public int add(Project project) {
        String[] tp = project.getTopPictures();
        String[] bp = project.getBottomPictures();
        String[] tt = project.getTopTags();
        int[] tts = project.getTopTagSorts();
        String[] bt = project.getBottomTags();
        int[] bts = project.getBottomTagSorts();
        projectMapper.add(project);
        int projectId = project.getId();
        addPictures(tp, projectId, (byte) 0);
        addPictures(bp, projectId, (byte) 1);
        addTags(tt, tts, projectId, (byte) 0);
        addTags(bt, bts, projectId, (byte) 1);
        return projectId;
    }

    public int delete(int id) {
        cacheUtil.invalid(cacheKey(id));
        applyMapper.delete(id);
        return projectMapper.delete(id);
    }

    private void addPictures(String[] paths, int projectId, byte type) {
        if (ArrayUtils.isEmpty(paths)) return;
        for (String path : paths) {
            Picture picture = new Picture();
            picture.setPath(path);
            picture.setProjectId(projectId);
            picture.setType(type);
            projectMapper.addPicture(picture);
        }
    }

    private int delPicture(int projectId, byte type) {
        return projectMapper.delPicture(projectId, type);
    }

    private void addTags(String[] names, int[] sorts, int projectId, byte type) {
        if (ArrayUtils.isEmpty(names)) return;
        for (int i=0; i < names.length; i++) {
            Tag tag = new Tag();
            tag.setName(names[i]);
            tag.setSort(sorts[i]);
            tag.setProjectId(projectId);
            tag.setType(type);
            projectMapper.addTag(tag);
        }
    }

    private int delTag(int projectId, byte type) {
        return projectMapper.delTag(projectId, type);
    }
}
