package com.blue.atom.service.impl;

import com.blue.atom.param.RoleMenuParam;
import com.blue.atom.entity.RoleMenu;
import com.blue.atom.mapper.RoleMenuMapper;
import com.blue.atom.service.IRoleMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liguowen
 * @since 2021-11-15
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    /**
     * 角色设置菜单
     *
     * @param param
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setRoleMenu(RoleMenuParam param) {
        LambdaQueryWrapper<RoleMenu> queryWrapper = Wrappers.lambdaQuery(RoleMenu.class);
        queryWrapper.eq(RoleMenu::getRoleId, param.getRoleId());
        roleMenuMapper.delete(queryWrapper);

        List<RoleMenu> roleMenuList = new ArrayList<>();
        List<Integer> menuIdList = param.getMenuIds();
        for (Integer menuId : menuIdList) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(menuId);
            roleMenu.setRoleId(param.getRoleId());
            roleMenuList.add(roleMenu);
        }
        saveBatch(roleMenuList);
    }
}
