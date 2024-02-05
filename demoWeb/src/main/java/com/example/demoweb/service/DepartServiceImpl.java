package com.example.demoweb.service;

import com.example.demoweb.bean.Depart;
import com.example.demoweb.mapper.DepartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departService")
public class DepartServiceImpl implements DepartService{
    @Autowired
    private DepartMapper departMapper;

    @Override
    public List<Depart> selectAll() {
        return departMapper.selectAll();
    }
}
