/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rstest.rsauthapi.model.controller;

import com.rstest.rsauthapi.model.Users;
import com.rstest.rsauthapi.model.repository.UsersInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rohit
 */
@RestController
@RequestMapping("/ur")
public class UsersController {
    
    @Autowired
    UsersInterface userRepo;
    
    @GetMapping("/users")
    public List<Users> getAllUsers() {
        List<Users> users = userRepo.findAll();
        
        System.out.println("user" + users);
        return users;
    }
    
    @GetMapping("/users/{user}")
    public String getusers(@PathVariable("user") String user) {
        
        Users u = userRepo.findByUser(user);
        System.out.println("check find " + u);
        if (u != null) {
            System.out.println("user" + u);
            return u.getUser();
        }
        return "User Not Found";
    }
    
    @PostMapping("/users/adduser")
    @ResponseBody
    public Users addUser(@RequestBody Users u) {
        System.out.println("user data " + u.getUser());
        userRepo.save(u);
        return u;
    }
    
    @PostMapping("/users/updateuser")
    @ResponseBody
    public Users updateUser(@RequestBody Users u) {
        Users updateUser = userRepo.findByUser(u.getUser());
        if(updateUser!=null){
        updateUser.setPassword(u.getPassword());
        
        System.out.println("user data " + updateUser.getUser());
        userRepo.save(updateUser);
        return updateUser;
        }
        return u;
    }
}
