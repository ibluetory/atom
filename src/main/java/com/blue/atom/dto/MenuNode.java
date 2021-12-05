package com.blue.atom.dto;

/**
 * 菜单树节点
 */

import com.blue.atom.entity.Menu;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MenuNode对象", description = "菜单树节点")
public class MenuNode {

    private Integer id;

    private String label;

    private List<MenuNode> children;

    public MenuNode(Menu menu) {
        this.id = menu.getId();
        this.label = menu.getTitle();
    }

    /**
     * 添加子菜单
     *
     * @param menuNode
     */
    public void addChild(MenuNode menuNode) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(menuNode);
    }
}
