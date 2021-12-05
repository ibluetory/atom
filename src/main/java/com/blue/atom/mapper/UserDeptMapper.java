package com.blue.atom.mapper;

import com.blue.atom.entity.UserDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户-部门 Mapper 接口
 * </p>
 *
 * @author liguowen
 * @since 2021-11-16
 */
public interface UserDeptMapper extends BaseMapper<UserDept> {

    /**
     * 根据用户id进行删除
     *
     * @param userId
     */
    void deleteByUserId(Integer userId);

    /**
     * 根据用户id进行查询
     *
     * @param userId
     * @return
     */
    List<Integer> selectByUserId(Integer userId);

    /**
     * 根据部门id进行删除
     *
     * @param deptId
     */
    void deleteByDeptId(Integer deptId);
}
