/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rstest.rsauthapi.model.controller;

import com.rstest.rsauthapi.model.Tokens;
import com.rstest.rsauthapi.model.Users;
import com.rstest.rsauthapi.model.repository.TokensInterface;
import com.rstest.rsauthapi.model.repository.UsersInterface;
import com.rstest.rsauthapi.model.service.GenerateAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rohit
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UsersInterface userRepo;

    @Autowired
    TokensInterface tokenRepo;
    
    @GetMapping("/lg")
    public String getAllTokens() {
        
        return "login ....";
    }

    @PostMapping("/authenticate")
    @ResponseBody
    public String authenticate(@RequestBody Users u) {
        Users checkUser = userRepo.findByUser(u.getUser());
        if (checkUser != null) {
            boolean userFound = checkUser.getPassword().equals(u.getPassword());
            if (userFound) {
                String token = new GenerateAuthToken().generateAuthToken();
                Tokens t = new Tokens();
                t.setUser(checkUser.getUser());
                t.setToken(token);
                tokenRepo.save(t);
                return token;
            }
            return "Wrong password";
        }
        return "user not found";
    }
    
//    @PostMapping("/authenticateToken")
//    @ResponseBody
//    public boolean authenticateToken(@RequestBody Tokens t) {
    public String authenticateToken(Tokens t) {
        Tokens checkToken = tokenRepo.findByUser(t.getUser());
        if (checkToken != null) {
            GenerateAuthToken ga=new GenerateAuthToken();
            boolean tokenValid = ga.checkAuthToken(checkToken.getToken()) && checkToken.getToken().equals(t.getToken());
            if (tokenValid) {
                checkToken.setToken(ga.generateAuthToken()); // update token timeout
                tokenRepo.save(checkToken);
                return checkToken.getToken();
            }
            return null; // token expired
        }
        return null; //not logged in
    }

}
