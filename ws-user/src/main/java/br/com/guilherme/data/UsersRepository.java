/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.data;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author guilherme.costa
 */
public interface UsersRepository extends JpaRepository<UserEntity,Long>{
    UserEntity findByEmail(String email);
}
