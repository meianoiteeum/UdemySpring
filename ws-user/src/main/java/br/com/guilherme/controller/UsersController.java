
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.controller;

import br.com.guilherme.model.CreateUserRequestModel;
import br.com.guilherme.model.CreateUserResponseModel;
import br.com.guilherme.service.UsersService;
import br.com.guilherme.shared.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author guilherme.costa
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    
    @Autowired
    private Environment env;
    
    @Autowired
    private UsersService service;
    
    @GetMapping("/status/check")
    public String status(){
        return "Working on port " + env.getProperty("local.server.port") + ", with token " + env.getProperty("token.secret");
    }
    
    @PostMapping
    public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody CreateUserRequestModel userDetails){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
        UserDTO dto = mapper.map(userDetails, UserDTO.class);
        
        CreateUserResponseModel returnValue = mapper.map(service.createUser(dto), CreateUserResponseModel.class);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
        
    }
    
    @GetMapping
    public String getUser(){
        return "Get user";
    }
    
    @PutMapping
    public String updateUser(){
        return "Update user";
    }
    
    @DeleteMapping
    public String deleteUser(){
        return "Delete user";
    }
}
