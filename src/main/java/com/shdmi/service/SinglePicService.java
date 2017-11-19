package com.shdmi.service;

import com.shdmi.dao.SinglePicMapper;
import com.shdmi.dao.WorksMapper;
import com.shdmi.entity.SinglePic;
import com.shdmi.entity.Works;
import com.shdmi.entity.WorksPicture;
import com.shdmi.utils.Page;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/11/18.
 */
@Service
public class SinglePicService {

    @Autowired
    private SinglePicMapper singlePicMapper;
    public int add(SinglePic singlePic) {
        return singlePicMapper.add(singlePic);
    }

    public int delete(int id) {
        return singlePicMapper.delete(id);
    }

    public Page<SinglePic> selecePic(int pageNumber, int pageSize) {
        int totalCount = singlePicMapper.count();
        Page<SinglePic> page = new Page<>();
        int index = 0;
        if (pageNumber != 0 && pageSize != 0) index = (pageNumber - 1) * pageSize;
        List<SinglePic> singlePics = singlePicMapper.selecePic(index, pageSize);
        if (pageNumber != 0 && pageSize != 0){
            page.setPageNo(pageNumber);
            page.setPageSize(pageSize);
        }
        page.setTotalCount(totalCount);
        page.setResult(singlePics);
        return page;
    }

    public Page<SinglePic> selecePicByType(Byte type, int pageNumber, int pageSize) {
        int totalCount = singlePicMapper.count();
        Page<SinglePic> page = new Page<>();
        int index = 0;
        if (pageNumber != 0 && pageSize != 0) index = (pageNumber - 1) * pageSize;
        List<SinglePic> singlePics = singlePicMapper.selecePicByType(type, index, pageSize);
        if (pageNumber != 0 && pageSize != 0){
            page.setPageNo(pageNumber);
            page.setPageSize(pageSize);
        }
        page.setTotalCount(totalCount);
        page.setResult(singlePics);
        return page;
    }

    public SinglePic selecePicById(int id){
        return singlePicMapper.selecePicById(id);
    }
}
