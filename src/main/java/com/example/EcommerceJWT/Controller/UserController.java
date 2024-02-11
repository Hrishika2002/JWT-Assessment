package com.example.EcommerceJWT.Controller;

import com.example.EcommerceJWT.Model.User;
import com.example.EcommerceJWT.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    UserService userServ;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.OK)
    public User save(@RequestBody User user){
        System.out.println("Check");
        return userServ.saveUser(user);
    }

    @GetMapping("/exists/email")
    public Boolean emailExists(@RequestBody User user){
        String userEmail = user.getEmail();
        return userServ.existByEmail(userEmail);
}

    @PostMapping("/login")
    public String loginUser(@RequestBody User user){
        String email = user.getEmail();
        String password = user.getPassword();
        return  userServ.userLogin(email, password);
    }
}
