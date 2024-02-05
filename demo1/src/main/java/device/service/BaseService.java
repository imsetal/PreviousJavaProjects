package cn.yzw.device.service;

import cn.yzw.device.vo.PageVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 基础服务接口
 * @param <E> 实体类泛型
 * @param <K> 实体类所对应数据表的id类型
 */
public interface BaseService<E,K> {

    /**
     * 获取所有数据
     * @return 返回所有数据
     * @throws Exception 抛出异常
     */
    List<E> listAll()throws Exception;

    /**
     * 通过id获取数据
     * @param id 查询id
     * @return 返回查询到的数据
     * @throws Exception 抛出异常
     */
    E getObjById(K id)throws Exception;

    /**
     * 通过id删除数据
     * @param id 删除的id
     * @return 删除成功返回true，失败返回false
     * @throws Exception 抛出异常
     */
    boolean deleteObjById(K id)throws Exception;

    /**
     * 修改数据
     * @param e 要修改的数据
     * @return 修改成功返回true，失败返回false
     * @throws Exception 抛出异常
     */
    boolean updateObj(E e)throws Exception;

    /**
     * 添加数据
     * @param e 要添加的数据
     * @return 添加成功返回true，失败返回false
     * @throws Exception 抛出异常
     */
    boolean addObj(E e)throws Exception;


    /**
     * 多参数查找数据
     * @param params 查询条件
     * @return 返回查询到的数据
     * @throws Exception 抛出异常
     */
    E getObj(String sql,Object...params)throws Exception;

    /**
     * 多参数删除数据
     * @param params 删除条件
     * @return 删除成功返回 true，失败返回false
     * @throws Exception 抛出异常
     */
    boolean deleteObj(Object...params)throws Exception;

    /***
     * 删除多条数据
     * @param ids id集合
     * @return 删除成功返回true，失败返回false
     * @throws Exception 抛出异常
     */
    boolean deleteObjList(Set<K> ids)throws Exception;

    /**
     * 多参数查询多条数据
     * @param params 查询添加
     * @return 返回数据集合
     * @throws Exception 抛出异常
     */
    List<E> listObj(String sql,Object...params)throws Exception;

    /**
     *
     * @param currentPage
     * @param rows
     * @param resources
     * @return
     * @throws Exception
     */
    PageVo<E> pageObj(String currentPage,String rows,Map<String,Object> resources)throws Exception;


}
