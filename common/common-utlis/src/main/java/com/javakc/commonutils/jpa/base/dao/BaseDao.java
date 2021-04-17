package com.javakc.commonutils.jpa.base.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * 公共组件(Repository接口)继承该接口后，即可获得CRUD功能
 * @author leesiyu
 * @version V1.0
 * @Copyright © 北京汇才同飞教育科技公司
 */
@NoRepositoryBean
public interface BaseDao<T, ID> extends JpaRepository<T, ID> {

    /**
     * 动态条件查询
     * @param specification 动态条件参数
     * @return
     */
    List<T> findAll(Specification<T> specification);

    /**
     * 动态条件查询带分页
     * @param specification 动态条件参数
     * @param pageable 分页参数
     * @return
     */
    Page<T> findAll(Specification<T> specification, Pageable pageable);

}
