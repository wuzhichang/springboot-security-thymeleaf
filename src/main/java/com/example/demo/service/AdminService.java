package com.example.demo.service;

import com.example.demo.dao.AdminDao;
import com.example.demo.entity.Admin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AdminService {

    @Resource
    private AdminDao adminDao;

    @Transactional(readOnly = true)
    public Admin findByUsername (String username){
        return adminDao.findByUsername(username);
    }
}
