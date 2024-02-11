package com.example.EcommerceJWT.Services;

import com.example.EcommerceJWT.Model.User;
import com.example.EcommerceJWT.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepo;
    private TokenService tokenService;
    @Autowired
    public UserService(UserRepository userRepo, TokenService tokenService) {
        this.userRepo = userRepo;
        this.tokenService = tokenService;
    }

    public User saveUser(User user){
        return userRepo.save(user);
    }


    public String userLogin(String email,String password){
        boolean foundUsers = existByEmail(email);
        if(foundUsers) {
            User userObj = userRepo.getUserByUsername(email);
            if(userObj.getPassword().equals(password)){
                return "{" +
                        "\"message\":"+"Successfully logged in\",\n"+
                        "\"data\":"+userObj+",\n"+
                        "\"Email: " + userObj.getEmail()+"\n"+
                        "\"token: "+ tokenService.createTokenFunction(userObj.getId())+"\""+
                        "}";
            }
        }

        return "{" +
                "\"message\":"+"Authentication failed\",\n"+
                "}";
    }
    public Boolean existByEmail(String email){
     Optional<User> usersObj = Optional.ofNullable(userRepo.getUserByUsername(email));
    if(!usersObj.isEmpty()){
        return true;
    }
    return false;
}

}
