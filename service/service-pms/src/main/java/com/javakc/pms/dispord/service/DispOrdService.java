package com.javakc.pms.dispord.service;

import com.javakc.commonutils.jpa.base.service.BaseService;
import com.javakc.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import com.javakc.pms.dispord.dao.DispOrdDao;
import com.javakc.pms.dispord.entity.DispOrd;
import com.javakc.pms.dispord.vo.DispOrdQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DispOrdService extends BaseService<DispOrdDao,DispOrd> {

//    @Autowired
//    private DispOrdDao dispOrdDao;

    /**
     * 查询所有调度指令库
     * @return
     */
    public List<DispOrd> findAll() {
        return dao.findAll();
    }


    public Page<DispOrd> findPage(DispOrdQuery dispOrdQuery, int pageNo, int pageSize){

        SimpleSpecificationBuilder<DispOrd> simpleSpecificationBuilder=new SimpleSpecificationBuilder();
        if (!StringUtils.isEmpty(dispOrdQuery.getOrderName()))
        {

            /**
             * 可用操作符
             * = 等值、!= 不等值 (字符串、数字)
             * >=、<=、>、< (数字)
             * ge，le，gt，lt(字符串)
             * :表示like %v%
             * l:表示 v%
             * :l表示 %v
             * null表示 is null
             * !null表示 is not null
             */
            simpleSpecificationBuilder.and("orderName", ":", dispOrdQuery.getOrderName());
        }

        if (!StringUtils.isEmpty(dispOrdQuery.getBeginDate())) {
            simpleSpecificationBuilder.and("gmtCreate", "ge" , dispOrdQuery.getBeginDate());
        }
        if (!StringUtils.isEmpty(dispOrdQuery.getEndDate())) {
            simpleSpecificationBuilder.and("gmtCreate", "lt", dispOrdQuery.getEndDate());
        }
        return dao.findAll(simpleSpecificationBuilder.getSpecification(), PageRequest.of(pageNo-1,pageSize));

    }
}