package com.blue.atom.controller;

import com.blue.atom.dto.ResultDTO;
import com.blue.atom.entity.Menu;
import com.blue.atom.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 菜单控制器
 * </p>
 *
 * @author liguowen
 * @since 2021-11-15
 */
@Api(tags = "MenuController")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private IMenuService  menuService;

    @ApiOperation(value = "列表查询")
    @GetMapping("/list")
    public ResultDTO getList() {
        return ResultDTO.success(menuService.list());
    }

    @ApiOperation(value = "菜单树")
    @GetMapping("/tree")
    public ResultDTO getMenuTree() {
        return ResultDTO.success(menuService.getMenuTree());
    }

    @ApiOperation(value = "添加或修改")
    @PostMapping("/saveOrUpdate")
    public ResultDTO saveOrUpdate(@RequestBody Menu Menu) {
        return ResultDTO.success(menuService.saveOrUpdate(Menu));
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete/{id}")
    public ResultDTO delete(@PathVariable int id) {
        menuService.deleteById(id);
        return ResultDTO.success();
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping("/{id}")
    public ResultDTO info(@PathVariable int id) {
        return ResultDTO.success(menuService.getById(id));
    }
}

