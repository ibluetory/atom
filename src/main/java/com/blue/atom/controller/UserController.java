package com.blue.atom.controller;

import com.blue.atom.annotation.SkipAuth;
import com.blue.atom.param.LoginParam;
import com.blue.atom.dto.ResultDTO;
import com.blue.atom.param.UserPageParam;
import com.blue.atom.entity.User;
import com.blue.atom.enums.ApiStatusCode;
import com.blue.atom.service.IMenuService;
import com.blue.atom.service.IUserService;
import com.blue.atom.util.AuthJwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户控制器
 * </p>
 *
 * @author liguowen
 * @since 2021-11-15
 */
@Api(tags = "UserController")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    HttpServletRequest request;
    @Resource
    private AuthJwtUtil authJwtUtil;
    @Resource
    private IUserService userService;
    @Resource
    private IMenuService menuService;


    @ApiOperation(value = "列表查询")
    @GetMapping("/list")
    public ResultDTO getList() {
        return ResultDTO.success(userService.list());
    }

    @ApiOperation(value = "分页")
    @PostMapping("/page")
    public ResultDTO pageList(@RequestBody UserPageParam param) {
        return ResultDTO.success(userService.pageList(param));
    }

    @ApiOperation(value = "添加或修改")
    @PostMapping("/saveOrUpdate")
    public ResultDTO saveOrUpdate(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
        return ResultDTO.success();
    }

    @ApiOperation(value = "删除")
    @GetMapping("/delete/{id}")
    public ResultDTO delete(@PathVariable int id) {
        userService.deleteById(id);
        return ResultDTO.success();
    }


    /**
     * 登录
     *
     * @return
     */
    @SkipAuth
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ResultDTO login(@RequestBody LoginParam param) {
        // 验证
        User user = userService.selectByMobileAndPassword(param.getMobile(), param.getPassword());
        if (user == null) {
            return ResultDTO.error(ApiStatusCode.NO_USER);
        }
        return ResultDTO.success(authJwtUtil.generateToken(user));
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/info")
    public ResultDTO getUserInfo() {
        String token = request.getHeader("authorization");
        if (StringUtils.isEmpty(token)) {
            return ResultDTO.error(ApiStatusCode.NO_TOKEN);
        }

        // 根据token匹配用户
        String userId = authJwtUtil.getUserId(token);
        User user = userService.getById(Integer.parseInt(userId));
        if (user == null) {
            return ResultDTO.error(ApiStatusCode.TOKEN_ERROR);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("name", user.getName());
        map.put("menu", menuService.getUserMenuTree(user.getId()));
        return ResultDTO.success(map);
    }

    /**
     * 退出
     *
     * @return
     */
    @SkipAuth
    @PostMapping("/logout")
    public ResultDTO logout() {
        // 清空缓存用户信息
        return ResultDTO.success();
    }
}

