package com.blue.atom.controller;

import com.blue.atom.param.PageParam;
import com.blue.atom.dto.ResultDTO;
import com.blue.atom.entity.Dept;
import com.blue.atom.service.IDeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 部门控制器
 * </p>
 *
 * @author liguowen
 * @since 2021-11-16
 */
@Api(tags = "DeptController")
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private IDeptService deptService;

    @ApiOperation(value = "列表查询")
    @GetMapping("/list")
    public ResultDTO getList() {
        return ResultDTO.success(deptService.list());
    }

    @ApiOperation(value = "部门树")
    @GetMapping("/tree")
    public ResultDTO getDeptTree() {
        return ResultDTO.success(deptService.getDeptTree());
    }

    @ApiOperation(value = "分页")
    @PostMapping("/page")
    public ResultDTO pageList(@RequestBody PageParam param) {
        Page<Dept> page = new Page<>(param.getCurrent(), param.getSize());
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<Dept>();
        queryWrapper.orderByDesc("id");
        return ResultDTO.success(deptService.page(page, queryWrapper));
    }

    @ApiOperation(value = "添加或修改")
    @PostMapping("/saveOrUpdate")
    public ResultDTO saveOrUpdate(@RequestBody Dept dept) {
        deptService.saveOrUpdateDept(dept);
        return ResultDTO.success();
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete/{id}")
    public ResultDTO delete(@PathVariable int id) {
        deptService.deleteById(id);
        return ResultDTO.success();
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping("/{id}")
    public ResultDTO info(@PathVariable int id) {
        return ResultDTO.success(deptService.findById(id));
    }

}

