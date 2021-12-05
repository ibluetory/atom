package com.blue.atom.dto;

/**
 * 部门树节点
 */

import com.blue.atom.entity.Dept;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "DeptNode对象", description = "部门树节点")
public class DeptNode {

    private Integer id;

    private String label;

    private List<DeptNode> children;

    public DeptNode(Dept dept) {
        this.id = dept.getId();
        this.label = dept.getName();
    }

    /**
     * 添加子部门
     *
     * @param deptNode
     */
    public void addChild(DeptNode deptNode) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(deptNode);
    }
}
