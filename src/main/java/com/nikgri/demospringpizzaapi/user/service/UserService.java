package com.nikgri.demospringpizzaapi.user.service;


import com.nikgri.demospringpizzaapi.user.entity.User;
import com.nikgri.demospringpizzaapi.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public void addNewUser(User user) {
        this.userRepository.save(user);
    }

    public void deleteUser(int id) {
        if(!this.userRepository.existsById(id)){
            throw new RuntimeException("No user with that Id is found!!!!");
        }
        this.userRepository.deleteById(id);
    }

    public void updateUser(int id, User user){
        if(this.userRepository.existsById(id)){
            User tempUser = this.userRepository.getOne(id);
            tempUser.setAge(user.getAge());
            tempUser.setEmail(user.getEmail());
            tempUser.setName(user.getName());
            tempUser.setPassword(user.getPassword());
            this.userRepository.save(tempUser);
        }else{
            throw new RuntimeException("No user with that Id is found!!!!");
        }

    }
}
