package com.blue.atom.service;

import com.blue.atom.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author liguowen
 * @since 2021-11-15
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(Integer id);
}
