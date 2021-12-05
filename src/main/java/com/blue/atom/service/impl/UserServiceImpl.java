package com.blue.atom.service.impl;

import com.blue.atom.param.UserPageParam;
import com.blue.atom.entity.User;
import com.blue.atom.entity.UserDept;
import com.blue.atom.entity.UserRole;
import com.blue.atom.mapper.UserDeptMapper;
import com.blue.atom.mapper.UserMapper;
import com.blue.atom.mapper.UserRoleMapper;
import com.blue.atom.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author liguowen
 * @since 2021-11-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserDeptMapper userDeptMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 新增或者修改用户
     *
     * @param user
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateUser(User user) {
        // 保存用户
        saveOrUpdate(user);

        // 保存部门
        List<Integer> deptIdList = user.getDeptIds();
        userDeptMapper.deleteByUserId(user.getId());
        for (Integer deptId : deptIdList) {
            UserDept userDept = new UserDept();
            userDept.setDeptId(deptId);
            userDept.setUserId(user.getId());
            userDeptMapper.insert(userDept);
        }

        // 保存角色
        List<Integer> roleIdList = user.getRoleIds();
        userRoleMapper.deleteByUserId(user.getId());
        for (Integer roleId : roleIdList) {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(user.getId());
            userRoleMapper.insert(userRole);
        }
    }

    /**
     * 分页查询
     *
     * @param param
     * @return
     */
    @Override
    public IPage<User> pageList(UserPageParam param) {
        Page<User> page = new Page<>(param.getCurrent(), param.getSize());
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.orderByDesc("id");
        IPage<User> pageResult = page(page, queryWrapper);
        for (User user : pageResult.getRecords()) {
            user.setDeptIds(userDeptMapper.selectByUserId(user.getId()));
            user.setRoleIds(userRoleMapper.selectByUserId(user.getId()));
        }
        return page;
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        // 删除用户关联角色
        userRoleMapper.deleteByUserId(id);
        // 删除用户关联部门
        userDeptMapper.deleteByUserId(id);
        // 删除用户
        removeById(id);
    }

    /**
     * 根据mobile和password查询
     *
     * @param mobile
     * @param password
     * @return
     */
    @Override
    public User selectByMobileAndPassword(String mobile, String password) {
        return userMapper.selectByMobileAndPassword(mobile, password);
    }
}
