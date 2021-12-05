package com.blue.atom.service.impl;

import com.blue.atom.entity.UserRole;
import com.blue.atom.mapper.UserRoleMapper;
import com.blue.atom.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色 服务实现类
 * </p>
 *
 * @author liguowen
 * @since 2021-11-15
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
