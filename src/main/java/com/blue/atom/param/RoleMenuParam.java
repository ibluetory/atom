package com.blue.atom.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 角色配置菜单参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleMenuParam {

    private Integer roleId;
    private List<Integer> menuIds;
}
