/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author guilherme.costa
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    
    @GetMapping("/status/check")
    public String status(){
        return "Working...";
    }
}
