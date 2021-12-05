package com.blue.atom.service;

import com.blue.atom.dto.DeptNode;
import com.blue.atom.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 部门 服务类
 * </p>
 *
 * @author liguowen
 * @since 2021-11-16
 */
public interface IDeptService extends IService<Dept> {

    /**
     * 查询部门树
     *
     * @return
     */
    List<DeptNode> getDeptTree();

    /**
     * 新增或者修改部门
     *
     * @param dept
     */
    void saveOrUpdateDept(Dept dept);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Dept findById(Integer id);

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(Integer id);
}
