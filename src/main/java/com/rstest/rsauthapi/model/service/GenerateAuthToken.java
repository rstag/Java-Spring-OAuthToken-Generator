/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rstest.rsauthapi.model.service;

import java.util.Random;
import java.time.Instant;
import java.util.Date;

/**
 *
 * @author Rohit
 */
public class GenerateAuthToken {

    public String generateAuthToken() {
        String token = "";
        String cipher = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890$-_#@";
        Random random = new Random();
        char[] ch = new char[16];
        for (int i = 0; i < 16; i++) {
            ch[i] = cipher.charAt(random.nextInt(cipher.length() - 1));
        }

        String chars = String.valueOf(ch);

        System.out.println("token " + chars);
        long currTime = Date.from(Instant.now()).getTime();

        // Add timeout HH*MM*SS*ms
        currTime += 60 * 60 * 1000; 

        String expTime = String.valueOf(currTime);
        System.out.println("exp " + expTime);
        token = chars.substring(0, 4)
                .concat(expTime.substring(0, 2))
                .concat(chars.substring(4, 6))
                .concat(expTime.substring(2, 5))
                .concat(chars.substring(6, 9))
                .concat(expTime.substring(5, 8))
                .concat(chars.substring(9))
                .concat(expTime.substring(8));

        return token;
    }

    public Boolean checkAuthToken(String token) {
        String tk = token.substring(4, 6)
                .concat(token.substring(8, 11))
                .concat(token.substring(14, 17))
                .concat(token.substring(24));

        long t = Long.parseLong(tk);
//        System.out.println("diff " + Date.from(Instant.ofEpochMilli(t)).getTime() + "**" + Date.from(Instant.now()).getTime());
        return Date.from(Instant.ofEpochMilli(t)).getTime() > Date.from(Instant.now()).getTime();
    }

//    public static void main(String[] args) {
//        String tk = new GenerateAuthToken().generateAuthToken();
//        System.out.println("tk " + tk);
//        try {
//            // To check token expiry time
////            sleep(90000);  
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//        System.out.println("res " + new GenerateAuthToken().checkAuthToken(tk));
//    }

}
