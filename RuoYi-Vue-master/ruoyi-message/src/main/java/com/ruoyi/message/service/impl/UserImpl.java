package com.ruoyi.message.service.impl;


import com.ruoyi.message.mapper.UserMapper;
import com.ruoyi.message.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论service实现类
 */
@Service
public class UserImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询某个部门人
     */
    @Override
    public List<Integer> selectByDepartmentid(Integer departmentid) {
        return userMapper.selectByDepartmentid(departmentid);
    }
    /**
     * 查询某个岗位人
     */
    @Override
    public List<Integer> selectByPositionid(Integer positionid) {
        return userMapper.selectByPositionid(positionid);
    } /**
     * 查询所有人
     */
    @Override
    public List<Integer> selectAll() {
        return userMapper.selectAll();
    }

}
