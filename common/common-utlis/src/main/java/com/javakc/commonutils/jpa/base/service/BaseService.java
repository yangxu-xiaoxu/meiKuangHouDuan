package com.javakc.commonutils.jpa.base.service;

import com.javakc.commonutils.jpa.base.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * 公共组件(Service实现类)
 * @author leesiyu
 * @version V1.0
 * @Copyright © 北京汇才同飞教育科技公司
 */
@Transactional(readOnly = true)
public class BaseService<D extends BaseDao, T>{

    @Autowired
    protected D dao;

    /**
     * 动态条件查询或查询所有
     * @param specification 动态条件参数
     * @return
     */
    public List<T> list(Specification specification) {
        return dao.findAll(specification);
    }

    /**
     * 保存或修改
     * @param entity 实体类
     * @return
     */
    @Transactional(readOnly = false)
    public T saveOrUpdate(T entity) {
        return (T) dao.save(entity);
    }

    /**
     * ID查询对象
     * @param id ID主键
     * @return
     */
    public T getById(Serializable id) {
        Optional<T> optional = this.dao.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    /**
     * ID删除
     * @param id ID主键
     */
    @Transactional(readOnly = false)
    public void removeById(Serializable id) {
        dao.deleteById(id);
    }

    /**
     * 对象删除
     * @param entity 实体
     */
    @Transactional(readOnly = false)
    public void remove(T entity) {
        dao.delete(entity);
    }

    /**
     * 批量删除
     * @param entitys
     */
    @Transactional(readOnly = false)
    public void removeBatch(List<T> entitys) {
        dao.deleteInBatch(entitys);
    }

}
