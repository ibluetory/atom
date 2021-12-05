package com.blue.atom.mapper;

import com.blue.atom.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author liguowen
 * @since 2021-11-15
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id查询菜单列表
     *
     * @param userId
     * @return
     */
    List<Menu> selectByUserId(Integer userId);

    /**
     * 根据菜单id查询子菜单
     *
     * @param id
     * @return
     */
    List<Integer> selectChildrenById(Integer id);
}
