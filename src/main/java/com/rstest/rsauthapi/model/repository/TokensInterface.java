/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rstest.rsauthapi.model.repository;

import com.rstest.rsauthapi.model.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Rohit
 */
public interface TokensInterface extends JpaRepository<Tokens, String> {

    public Tokens findByUser(String user);
    
}
