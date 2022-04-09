/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rstest.rsauthapi.model.controller;

import com.rstest.rsauthapi.model.Tokens;
import com.rstest.rsauthapi.model.repository.TokensInterface;
import com.rstest.rsauthapi.model.service.GenerateAuthToken;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/tks")
public class TokensController {

    @Autowired
    TokensInterface tokenRepo;

    @GetMapping("/tokens")
    public List<Tokens> getAllTokens() {
        List<Tokens> tokens = tokenRepo.findAll();

        System.out.println("user" + tokens);
        return tokens;
    }

    @GetMapping("/gettoken/{user}")
    public String getusers(@PathVariable("user") String user) {
        Tokens u = tokenRepo.findByUser(user);
        if (u != null) {
            System.out.println("user" + u);
            return u.getUser();
        }
        return "User Not Found";
    }

    @PostMapping("/tokens/addtoken")
    @ResponseBody
    public Tokens addTokens(@RequestBody Tokens u) {
        System.out.println("user data " + u.getUser());
        GenerateAuthToken gta=new GenerateAuthToken();
        System.out.println("my token "+(gta.generateAuthToken()));
        tokenRepo.save(u);
        return u;
    }

    @PostMapping("/tokens/updatetoken")
    @ResponseBody
    public Tokens updateTokens(@RequestBody Tokens u) {
        Tokens updateToken = tokenRepo.findByUser(u.getUser());
        if (updateToken != null) {
            updateToken.setToken(u.getToken());

            System.out.println("user data " + updateToken.getUser());
            tokenRepo.save(updateToken);
            return updateToken;
        }
        return u;
    }
}
