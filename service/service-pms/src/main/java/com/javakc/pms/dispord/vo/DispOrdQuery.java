package com.javakc.pms.dispord.vo;

import lombok.Data;

import java.util.Date;

//查询条件的实体类
@Data
public class DispOrdQuery {

    private String orderName;
    private String beginDate;
    private String endDate;

}
