package cn.yzw.device.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * 基础dao
 * @param <E> 相关实体类泛型
 * @param <K> 实体类主键类型
 * @author 杨子威
 */
public interface BaseDao<E,K> {

    /**
     * 查询所有数据
     * @return
     */
    List<E> findAll() throws Exception;

    /**
     * 更新数据
     * @param e
     * @return
     */
    int update(E e)throws Exception;

    /**
     * 新增数据
     * @param e
     * @return
     */
    int insert(E e)throws Exception;

    /**
     * 通过id删除数据
     * @param id
     * @return
     */
    int deleteById(K id)throws Exception;

    /**
     * 通过id查找数据
     * @param id
     * @return
     */
    E findById(K id)throws Exception;







}
