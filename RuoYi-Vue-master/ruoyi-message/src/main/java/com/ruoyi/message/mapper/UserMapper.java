package com.ruoyi.message.mapper;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {


    /**
     * 查询某个部门人
     */
    public List<Integer> selectByDepartmentid(Integer departmentid);

    /**
     * 查询某个岗位人
     */
    public List<Integer> selectByPositionid(Integer positionid);
    /**
     * 查询所有人
     */
    public List<Integer> selectAll();
}
