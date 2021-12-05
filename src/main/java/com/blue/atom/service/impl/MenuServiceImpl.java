package com.blue.atom.service.impl;

import com.blue.atom.dto.MenuNode;
import com.blue.atom.entity.Menu;
import com.blue.atom.mapper.MenuMapper;
import com.blue.atom.mapper.RoleMenuMapper;
import com.blue.atom.service.IMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author liguowen
 * @since 2021-11-15
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    /**
     * 查询菜单树
     *
     * @return
     */
    @Override
    public List<MenuNode> getMenuTree() {
        LambdaQueryWrapper<Menu> queryWrapper = Wrappers.lambdaQuery(Menu.class);
        queryWrapper.orderByAsc(Menu::getSort, Menu::getId);
        List<Menu> menuList = menuMapper.selectList(queryWrapper);
        if (menuList == null || menuList.isEmpty()) {
            return null;
        }

        Map<Integer, MenuNode> cache = new HashMap<>();
        for (Menu menu : menuList) {
            MenuNode node = new MenuNode(menu);
            cache.put(node.getId(), node);
        }

        // 根据节点与父节点关系处理数据
        for (Menu menu : menuList) {
            if (menu.getParentId() == null) {
                continue;
            }

            if (cache.containsKey(menu.getParentId())) {
                cache.get(menu.getParentId()).addChild(cache.get(menu.getId()));
            }
        }

        List<MenuNode> menuNodeList = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.getParentId() == null) {
                menuNodeList.add(cache.get(menu.getId()));
            }
        }
        return menuNodeList;
    }

    /**
     * 查询用户菜单树
     *
     * @param userId
     * @return
     */
    @Override
    public List<Menu> getUserMenuTree(Integer userId) {
        // 部门角色菜单和用户角色菜单的合集
        List<Menu> menuList = menuMapper.selectByUserId(userId);
        if (menuList == null || menuList.isEmpty()) {
            return new ArrayList<>();
        }

        Map<Integer, Menu> cache = new HashMap<>();
        for (Menu menu : menuList) {
            cache.put(menu.getId(), menu);
        }

        // 根据节点与父节点关系处理数据
        for (Menu menu : menuList) {
            if (menu.getParentId() == null) {
                continue;
            }

            if (cache.containsKey(menu.getParentId())) {
                cache.get(menu.getParentId()).addChild(cache.get(menu.getId()));
            }
        }

        List<Menu> menuNodeList = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.getParentId() == null) {
                menuNodeList.add(cache.get(menu.getId()));
            }
        }

        // 排序
        Collections.sort(menuNodeList, Comparator.comparing(Menu::getSort));
        return menuNodeList;
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        // 获取子菜单, 并把当前菜单放入待删除列表
        List<Integer> menuIdList = getChildrenList(id);
        menuIdList.add(id);
        // 删除菜单
        menuMapper.deleteBatchIds(menuIdList);
        // 删除角色已配置菜单
        roleMenuMapper.batchDeleteByMenuId(menuIdList);
    }

    /**
     * 获取子菜单列表
     *
     * @param id
     * @return
     */
    @Override
    public List<Integer> getChildrenList(Integer id) {
        List<Integer> menuIdList = menuMapper.selectChildrenById(id);
        for (Integer menuId : menuIdList) {
            List<Integer> subMenuIdList = menuMapper.selectChildrenById(menuId);
            if (subMenuIdList != null && subMenuIdList.size() > 0) {
                menuIdList.addAll(subMenuIdList);
            }
        }
        return menuIdList;
    }
}
