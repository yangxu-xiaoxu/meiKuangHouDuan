package com.javakc.commonutils.jpa.dynamic;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 创建SimpleSpecification来实现Specification接口，并且根据条件生成Specification对象，因为在最后查询的时候需要这个对象
 * SimpleSpecification是核心类型，用来根据条件生成Specification对象，这个SimpleSpecification直接存储了具体的查询条件。
 * @author leesiyu
 * @version V1.0
 * @Copyright © 北京汇才同飞教育科技公司
 */
public class SimpleSpecification<T> implements Specification<T> {

    /**
     * 查询的条件列表，是一组列表
     */
    private List<SpecificationOperator> opers;

    public SimpleSpecification(List<SpecificationOperator> opers){
        this.opers=opers;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        int index = 0;
        Predicate resultPre = null;
        for(SpecificationOperator so :opers){
            if(index++==0){//第一次index=0    index++是先赋值再加
                resultPre = generatePredicate(root, criteriaBuilder, so);
                continue;
            }
            Predicate pre = generatePredicate(root, criteriaBuilder, so);
            if(pre==null)continue;
            if("and".equalsIgnoreCase(so.getJoin())){
                resultPre = criteriaBuilder.and(resultPre, pre);
            }else if("or".equalsIgnoreCase(so.getJoin())){
                resultPre = criteriaBuilder.or(resultPre, pre);
            }
        }
        return resultPre;
    }

    private Predicate generatePredicate(Root<T> root,CriteriaBuilder criteriaBuilder,SpecificationOperator so){
        if(so != null && !StringUtils.isEmpty(so.getOper())){
            if("=".equalsIgnoreCase(so.getOper())&&so.getValue()!=null){
                return criteriaBuilder.equal(root.get(so.getKey()), so.getValue());
            }else if(">=".equalsIgnoreCase(so.getOper())&&so.getValue()!=null){
                return criteriaBuilder.ge(root.get(so.getKey()).as(Number.class),(Number) so.getValue());
            }else if("<=".equalsIgnoreCase(so.getOper())&&so.getValue()!=null){
                return criteriaBuilder.le(root.get(so.getKey()).as(Number.class),(Number)so.getValue());
            }else if(">".equalsIgnoreCase(so.getOper())&&so.getValue()!=null){
                return criteriaBuilder.gt(root.get(so.getKey()).as(Number.class), (Number) so.getValue());
            }else if("<".equalsIgnoreCase(so.getOper())&&so.getValue()!=null){
                return criteriaBuilder.lt(root.get(so.getKey()).as(Number.class), (Number) so.getValue());
            }else if(":".equalsIgnoreCase(so.getOper())&&so.getValue()!=null){
                return criteriaBuilder.like(root.get(so.getKey()).as(String.class), "%" + so.getValue() + "%");
            }else if(":l".equalsIgnoreCase(so.getOper())&&so.getValue()!=null){
                return criteriaBuilder.like(root.get(so.getKey()).as(String.class), "%" + so.getValue());
            }else if("l:".equalsIgnoreCase(so.getOper())&&so.getValue()!=null){
                return criteriaBuilder.like(root.get(so.getKey()).as(String.class), so.getValue() + "%");
            }else if("null".equalsIgnoreCase(so.getOper())){
                return criteriaBuilder.isNull(root.get(so.getKey()));
            }else if("!null".equalsIgnoreCase(so.getOper())){
                return criteriaBuilder.isNotNull(root.get(so.getKey()));
            }else if("!=".equalsIgnoreCase(so.getOper())&&so.getValue()!=null){
                return criteriaBuilder.notEqual(root.get(so.getKey()), so.getValue());
            }else if("ge".equalsIgnoreCase(so.getOper())&&so.getValue()!=null){
                return criteriaBuilder.greaterThanOrEqualTo(root.get(so.getKey()).as(String.class), so.getValue().toString());
            }else if("le".equalsIgnoreCase(so.getOper())&&so.getValue()!=null){
                return criteriaBuilder.lessThanOrEqualTo(root.get(so.getKey()).as(String.class), so.getValue().toString());
            }else if("gt".equalsIgnoreCase(so.getOper())&&so.getValue()!=null){
                return criteriaBuilder.greaterThan(root.get(so.getKey()).as(String.class), so.getValue().toString());
            }else if("lt".equalsIgnoreCase(so.getOper())&&so.getValue()!=null){
                return criteriaBuilder.lessThan(root.get(so.getKey()).as(String.class), so.getValue().toString());
            }
        }
        return null;
    }

}
