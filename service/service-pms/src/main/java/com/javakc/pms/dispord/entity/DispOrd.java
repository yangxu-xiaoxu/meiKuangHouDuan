package com.javakc.pms.dispord.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "dispatching_command")
@EntityListeners(AuditingEntityListener.class)
public class DispOrd {
    @Column(name = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "priority")
    private String proproty;

    @Column(name = "rtcw")
    private String rtcw;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "order_cbd")
    private String orderCbd;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "gmt_create",nullable = false,updatable = false)
    private Date gmtCreate;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "gmt_modified",nullable = false,insertable = false)
    private Date gmtModified;

}