package cn.yzw.device.service;

import cn.yzw.device.vo.Category;

import java.util.List;

public interface CategoryService extends BaseService<Category,Integer>  {

    List<Category> toTree()throws Exception;



}
