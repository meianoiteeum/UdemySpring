/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.service;

import br.com.guilherme.shared.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author guilherme.costa
 */
public interface UsersService extends UserDetailsService {
    UserDTO createUser(UserDTO userdetails);
    UserDTO getUserDetailsByEmail(String email);
}
