package com.blue.atom.controller;

import com.blue.atom.param.RoleMenuParam;
import com.blue.atom.mapper.RoleMenuMapper;
import com.blue.atom.service.IRoleMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blue.atom.param.PageParam;
import com.blue.atom.dto.ResultDTO;
import com.blue.atom.entity.Role;
import com.blue.atom.service.IRoleService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色控制器
 * </p>
 *
 * @author liguowen
 * @since 2021-11-15
 */
@Api(tags = "RoleController")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService  roleService;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private IRoleMenuService roleMenuService;

    @ApiOperation(value = "列表查询")
    @GetMapping("/list")
    public ResultDTO getList() {
        return ResultDTO.success(roleService.list());
    }

    @ApiOperation(value = "分页")
    @PostMapping("/page")
    public ResultDTO pageList(@RequestBody PageParam param) {
        Page<Role> page = new Page<>(param.getCurrent(), param.getSize());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
        queryWrapper.orderByDesc("id");
        return ResultDTO.success(roleService.page(page, queryWrapper));
    }

    @ApiOperation(value = "添加或修改")
    @PostMapping("/saveOrUpdate")
    public ResultDTO saveOrUpdate(@RequestBody Role role) {
        return ResultDTO.success(roleService.saveOrUpdate(role));
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete/{id}")
    public ResultDTO delete(@PathVariable int id) {
        roleService.deleteById(id);
        return ResultDTO.success();
    }

    @ApiOperation(value = "角色获取已配置菜单")
    @GetMapping("/menuTree/{roleId}")
    public ResultDTO getRoleMenuTree(@PathVariable int roleId) {
        return ResultDTO.success(roleMenuMapper.selectByRoleId(roleId));
    }

    @ApiOperation(value = "角色设置菜单")
    @PostMapping("/setMenuTree")
    public ResultDTO setMenuTree(@RequestBody RoleMenuParam param) {
        roleMenuService.setRoleMenu(param);
        return ResultDTO.success();
    }
}

