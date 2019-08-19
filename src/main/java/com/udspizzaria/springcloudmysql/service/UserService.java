package com.udspizzaria.springcloudmysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.udspizzaria.springcloudmysql.model.User;
import com.udspizzaria.springcloudmysql.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public List<User> findAll(){
    	return userRepository.findAll();
    }
    
    public ResponseEntity<User> findById(long id){
        return userRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
     }
    
    public User create(User user){
       return userRepository.save(user);
    }
    
    public ResponseEntity<User> update(long id, User user) {
       return userRepository.findById(id)
               .map(record -> {
                   record.setName(user.getName());
                   record.setEmail(user.getEmail());
                   record.setPhone(user.getPhone());
                   User updated = userRepository.save(record);
                   return ResponseEntity.ok().body(updated);
               }).orElse(ResponseEntity.notFound().build());
    }
    
    public ResponseEntity<?> delete(long id) {
       return userRepository.findById(id)
               .map(record -> {
            	   userRepository.deleteById(id);
                   return ResponseEntity.ok().build();
               }).orElse(ResponseEntity.notFound().build());
    }
}
