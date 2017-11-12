package com.shdmi.dao;

import com.shdmi.entity.Picture;
import com.shdmi.entity.Project;
import com.shdmi.entity.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by vincent on 16/3/20.
 */
public interface ProjectMapper {

    int count(@Param("title") String title);

    List<Project> findList(@Param("title") String title, @Param("index") int index, @Param("pageSize") int pageSize);

    Project get(int id);

    int update(Project project);

    int add(Project project);

    int delete(int id);

    int addPicture(Picture picture);

    int delPicture(@Param("projectId") int projectId, @Param("type") byte type);

    String[] findPictures(@Param("projectId") int projectId, @Param("type") byte type);

    int addTag(Tag tag);

    int delTag(@Param("projectId") int projectId, @Param("type") byte type);

    String[] findTags(@Param("projectId") int projectId, @Param("type") byte type);
}
