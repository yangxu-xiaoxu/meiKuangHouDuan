package com.javakc.pms.dispord.controller;


import com.javakc.commonutils.api.APICODE;
import com.javakc.pms.dispord.entity.DispOrd;
import com.javakc.pms.dispord.service.DispOrdService;
import com.javakc.pms.dispord.vo.DispOrdQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "调度指令库管理")
@RestController
@CrossOrigin
@RequestMapping("/pms/dispord")
public class DispOrdController {

    @Autowired
    private DispOrdService dispOrdService;


    @ApiOperation(value = "查询所有指令库")
    @GetMapping
    public APICODE findAll() {

        List<DispOrd> dispOrdList = dispOrdService.findAll();
        return APICODE.OK().data("items", dispOrdList);
    }

    @ApiOperation("带条件的自动查询")
    @PostMapping("{pageNo}/{pageSize}")
    public APICODE findPage(
            @RequestBody(required = false)DispOrdQuery dispOrdQuery,
            @PathVariable("pageNo") @ApiParam(name = "pageNo",value = "页码",required = true) int pageNo,
            @PathVariable("pageSize") @ApiParam(name = "pageSize",value = "每页最大条数",required = true) int pageSize
            ){
        Page<DispOrd> page= dispOrdService.findPage(dispOrdQuery, pageNo, pageSize);
        //总条数
        long totalElement = page.getTotalElements();
        //数据
        List<DispOrd> list = page.getContent();
       return APICODE.OK().data("total",totalElement).data("items",list);
    }

    @ApiOperation(value = "新增调度指令库")
    @PostMapping()
    public APICODE saveDispOrd(@RequestBody DispOrd dispOrd) {
        dispOrdService.saveOrUpdate(dispOrd);
        return APICODE.OK();
    }

    @ApiOperation(value = "根据ID获取调度指令库数据")
    @GetMapping("{dispOrdId}")
    public APICODE getDispOrdById(@PathVariable(name = "dispOrdId") int dispOrdId) {
        DispOrd dispOrd = dispOrdService.getById(dispOrdId);
        return APICODE.OK().data("dispOrd", dispOrd);
    }

    @ApiOperation(value = "修改调度指令库")
    @PutMapping()
    public APICODE updateDispOrd(@RequestBody DispOrd dispOrd) {
        dispOrdService.saveOrUpdate(dispOrd);
        return APICODE.OK();
    }

    @ApiOperation(value = "根据ID删除调度指令库")
    @DeleteMapping("{dispOrdId}")
    public APICODE deleteDispOrdById(@PathVariable(name = "dispOrdId") int dispOrdId) {
        dispOrdService.removeById(dispOrdId);
        return APICODE.OK();
    }

}