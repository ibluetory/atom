package com.blue.atom.mapper;

import com.blue.atom.entity.DeptRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 部门-角色 Mapper 接口
 * </p>
 *
 * @author liguowen
 * @since 2021-11-16
 */
public interface DeptRoleMapper extends BaseMapper<DeptRole> {

    /**
     * 根据部门id进行删除
     *
     * @param deptId
     */
    void deleteByDeptId(Integer deptId);

    /**
     * 根据roleId删除
     *
     * @param roleId
     */
    void deleteByRoleId(Integer roleId);

    /**
     * 根据部门id进行查询
     *
     * @param deptId
     * @return
     */
    List<Integer> selectByDeptId(Integer deptId);
}
