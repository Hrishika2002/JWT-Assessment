package com.example.EcommerceJWT.Repository;

import com.example.EcommerceJWT.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //SELECT * FROM USER WHERE EMAIL=:USERNAME
    @Query(value = "SELECT u FROM User u WHERE u.email = :userName")
    User getUserByUsername(String userName);

}
