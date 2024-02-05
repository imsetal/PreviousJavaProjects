package com.example.demoweb.mapper;

import com.example.demoweb.bean.Depart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartMapper {
    @Select({
            "select",
            "id, depName, grades",
            "from departments"
    })
    List<Depart> selectAll();
}
