package com.shdmi.service;

import com.shdmi.dao.AdminMapper;
import com.shdmi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vincent on 16/3/20.
 */
@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;
    public Admin login(String username, String password){
        return adminMapper.login(username, password);
    }
}
