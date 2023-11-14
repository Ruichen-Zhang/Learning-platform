package com.ruoyi.message.controller;


import com.ruoyi.message.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;


@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;



    public HashSet selectUser(Integer consumerid,Integer consumerjudge){
        HashSet<Integer> useridlist= new HashSet<>();
        if(consumerjudge==0) {
            useridlist.add(consumerid);
            return useridlist;
        }
        else if(consumerjudge==1){
            List<Integer> list=userService.selectByDepartmentid(consumerid);
            System.out.println(list);
            useridlist.addAll(list);
            return useridlist;
        }
        else if(consumerjudge==2){
            List<Integer> list=userService.selectByPositionid(consumerid);
            System.out.println(list);
            useridlist.addAll(list);
            return useridlist;
        }
        else{
            List<Integer> list=userService.selectAll();
            System.out.println(list);
            useridlist.addAll(list);
            return useridlist;
        }

    }


}


















