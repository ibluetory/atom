package com.blue.atom.mapper;

import com.blue.atom.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author liguowen
 * @since 2021-11-15
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据mobile和password查询
     *
     * @param mobile
     * @param password
     * @return
     */
    User selectByMobileAndPassword(String mobile, String password);

}
