/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.service;

import br.com.guilherme.data.UserEntity;
import br.com.guilherme.data.UsersRepository;
import br.com.guilherme.shared.UserDTO;
import java.util.ArrayList;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author guilherme.costa
 */
@Service
public class UsersServiceImpl implements UsersService{
    
    @Autowired
    private UsersRepository repository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO createUser(UserDTO userdetails) {
        userdetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userdetails.getPassword()));
        userdetails.setUserId(UUID.randomUUID().toString());
        
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
        UserEntity entity = mapper.map(userdetails, UserEntity.class);
        
        UserEntity newEntity = repository.save(entity);        
        
        UserDTO dto = mapper.map(newEntity, UserDTO.class);
        return dto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByEmail(username);
        if(userEntity == null)
            throw new UsernameNotFoundException(username);
        
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
    }

    @Override
    public UserDTO getUserDetailsByEmail(String email) {
        UserEntity userEntity = repository.findByEmail(email);
        if(userEntity == null)
            throw new UsernameNotFoundException(email);
        
        return new ModelMapper().map(userEntity,UserDTO.class);
    }
    
}
