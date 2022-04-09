/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rstest.rsauthapi.model.repository;

import com.rstest.rsauthapi.model.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Rohit
 */
//@RepositoryRestResource(collectionResourceRel = "users",path = "users")

public interface UsersInterface extends JpaRepository<Users, String> {
    public Users findByUser(String user);
}
