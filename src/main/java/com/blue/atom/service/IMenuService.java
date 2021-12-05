package com.blue.atom.service;

import com.blue.atom.dto.MenuNode;
import com.blue.atom.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author liguowen
 * @since 2021-11-15
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 查询菜单树
     *
     * @return
     */
    List<MenuNode> getMenuTree();

    /**
     * 查询用户菜单树
     *
     * @param userId
     * @return
     */
    List<Menu> getUserMenuTree(Integer userId);

    /**
     * 获取子菜单列表
     *
     * @param id
     * @return
     */
    List<Integer> getChildrenList(Integer id);

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(Integer id);
}
