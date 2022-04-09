/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rstest.rsauthapi.model.controller;

import com.rstest.rsauthapi.model.Tokens;
import com.rstest.rsauthapi.model.repository.TokensInterface;
import com.rstest.rsauthapi.model.repository.UsersInterface;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rohit
 */
@RestController
@RequestMapping("/profile")
public class ProfileController {
    
    @Autowired
    UsersInterface userRepo;

    @Autowired
    TokensInterface tokenRepo;
    
    @GetMapping("/me")
//    public String myProfile(@RequestBody String json) {
    public String getAllTokens(@RequestBody String user,@RequestHeader(value="auth_token") String auth_token) {
//        JSONObject jsonObj=new JSONObject(json);

        LoginController lg=new LoginController();
        Tokens t=new Tokens();
        t.setUser(user);
        t.setToken(auth_token);
        String auth = lg.authenticateToken(t);
        if(auth!= null){
        return t.toString();
        }
        return "token invalid ....";
    }
}
