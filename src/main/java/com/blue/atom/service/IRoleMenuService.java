package com.blue.atom.service;

import com.blue.atom.param.RoleMenuParam;
import com.blue.atom.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liguowen
 * @since 2021-11-15
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    /**
     * 角色设置菜单
     *
     * @param param
     */
    void setRoleMenu(RoleMenuParam param);
}
