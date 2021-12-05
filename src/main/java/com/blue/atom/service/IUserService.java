package com.blue.atom.service;

import com.blue.atom.param.UserPageParam;
import com.blue.atom.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author liguowen
 * @since 2021-11-15
 */
public interface IUserService extends IService<User> {

    /**
     * 新增或者修改用户
     *
     * @param user
     */
    void saveOrUpdateUser(User user);

    /**
     * 分页查询
     *
     * @param param
     * @return
     */
    IPage<User> pageList(UserPageParam param);

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据mobile和password查询
     *
     * @param mobile
     * @param password
     * @return
     */
    User selectByMobileAndPassword(String mobile, String password);
}
