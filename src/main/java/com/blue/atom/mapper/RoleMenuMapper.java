package com.blue.atom.mapper;

import com.blue.atom.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author liguowen
 * @since 2021-11-15
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 根据角色id进行查询
     *
     * @param roleId
     * @return
     */
    List<Integer> selectByRoleId(Integer roleId);

    /**
     * 根据菜单id删除
     *
     * @param menuId
     * @return
     */
    boolean deleteByMenuId(Integer menuId);

    /**
     * 根据菜单id列表批量删除
     *
     * @param menuIdList
     * @return
     */
    int batchDeleteByMenuId(List<Integer> menuIdList);

    /**
     * 根据角色id删除
     *
     * @param roleId
     * @return
     */
    boolean deleteByRoleId(Integer roleId);
}
