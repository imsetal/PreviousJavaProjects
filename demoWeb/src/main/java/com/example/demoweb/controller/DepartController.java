package com.example.demoweb.controller;

import com.example.demoweb.bean.Depart;
import com.example.demoweb.service.DepartService;
import com.example.demoweb.service.DepartServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/depart")
public class DepartController {
    @Resource
    private DepartService departService = new DepartServiceImpl();

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public List<Depart> selectAll() {
        List<Depart> list = departService.selectAll();
        return list;
    }
}
