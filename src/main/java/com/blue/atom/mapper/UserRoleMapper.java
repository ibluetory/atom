package com.blue.atom.mapper;

import com.blue.atom.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户-角色 Mapper 接口
 * </p>
 *
 * @author liguowen
 * @since 2021-11-15
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据用户id进行删除
     *
     * @param userId
     */
    void deleteByUserId(Integer userId);

    /**
     * 根据roleId删除
     *
     * @param roleId
     */
    void deleteByRoleId(Integer roleId);

    /**
     * 根据用户id进行查询
     *
     * @param userId
     * @return
     */
    List<Integer> selectByUserId(Integer userId);
}
