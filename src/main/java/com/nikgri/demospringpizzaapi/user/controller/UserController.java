package com.nikgri.demospringpizzaapi.user.controller;


import com.nikgri.demospringpizzaapi.user.entity.User;
import com.nikgri.demospringpizzaapi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(produces = "application/json" )
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping()
    public void addNewStudent(@RequestBody User user){
        System.out.println(user);
        this.userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteStudent(@PathVariable("userId") int id){
        this.userService.deleteUser(id);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") int id, @RequestBody User user ){
        this.userService.updateUser(id, user);
    }

}
