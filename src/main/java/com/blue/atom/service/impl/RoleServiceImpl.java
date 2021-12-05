package com.blue.atom.service.impl;

import com.blue.atom.entity.Role;
import com.blue.atom.mapper.RoleMapper;
import com.blue.atom.mapper.RoleMenuMapper;
import com.blue.atom.mapper.UserRoleMapper;
import com.blue.atom.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author liguowen
 * @since 2021-11-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * 根据id删除
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        // 删除角色对应菜单配置
        roleMenuMapper.deleteByRoleId(id);
        // 删除角色对应用户配置
        userRoleMapper.deleteByRoleId(id);
        // 删除角色
        removeById(id);
    }
}
