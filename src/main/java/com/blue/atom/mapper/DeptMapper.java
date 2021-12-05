package com.blue.atom.mapper;

import com.blue.atom.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 部门 Mapper 接口
 * </p>
 *
 * @author liguowen
 * @since 2021-11-16
 */
public interface DeptMapper extends BaseMapper<Dept> {

    /**
     * 设置子部门父节点id为空
     *
     * @param id
     */
    void resetChildren(int id);
}
