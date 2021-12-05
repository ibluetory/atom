package com.blue.atom.service.impl;

import com.blue.atom.dto.DeptNode;
import com.blue.atom.entity.Dept;
import com.blue.atom.entity.DeptRole;
import com.blue.atom.mapper.DeptMapper;
import com.blue.atom.mapper.DeptRoleMapper;
import com.blue.atom.mapper.UserDeptMapper;
import com.blue.atom.service.IDeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author liguowen
 * @since 2021-11-16
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Resource
    private DeptMapper deptMapper;
    @Resource
    private DeptRoleMapper deptRoleMapper;
    @Resource
    private UserDeptMapper userDeptMapper;

    /**
     * 查询部门树
     *
     * @return
     */
    @Override
    public List<DeptNode> getDeptTree() {
        QueryWrapper<Dept> deptWrapper = new QueryWrapper<Dept>();
        deptWrapper.orderByAsc("sort", "id");
        List<Dept> deptList = deptMapper.selectList(deptWrapper);
        if (deptList == null || deptList.isEmpty()) {
            return null;
        }

        Map<Integer, DeptNode> cache = new HashMap<>();
        for (Dept dept : deptList) {
            DeptNode node = new DeptNode(dept);
            cache.put(node.getId(), node);
        }

        // 根据节点与父节点关系处理数据
        for (Dept dept : deptList) {
            if (dept.getParentId() == null) {
                continue;
            }

            if (cache.containsKey(dept.getParentId())) {
                cache.get(dept.getParentId()).addChild(cache.get(dept.getId()));
            }
        }

        List<DeptNode> deptNodeList = new ArrayList<>();
        for (Dept dept : deptList) {
            if (dept.getParentId() == null) {
                deptNodeList.add(cache.get(dept.getId()));
            }
        }
        return deptNodeList;
    }

    /**
     * 新增或者修改部门
     *
     * @param dept
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateDept(Dept dept) {
        // 保存部门
        saveOrUpdate(dept);

        // 保存角色
        List<Integer> roleIdList = dept.getRoleIds();
        deptRoleMapper.deleteByDeptId(dept.getId());
        for (Integer roleId : roleIdList) {
            DeptRole deptRole = new DeptRole();
            deptRole.setRoleId(roleId);
            deptRole.setDeptId(dept.getId());
            deptRoleMapper.insert(deptRole);
        }
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public Dept findById(Integer id) {
        Dept dept = getById(id);
        dept.setRoleIds(deptRoleMapper.selectByDeptId(id));
        return dept;
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        // 删除部门关联角色
        deptRoleMapper.deleteByDeptId(id);
        // 删除该部门下的用户
        userDeptMapper.deleteByDeptId(id);
        // 该部门对应的子部门父节点置为空
        deptMapper.resetChildren(id);
        // 删除部门
        removeById(id);
    }

}
