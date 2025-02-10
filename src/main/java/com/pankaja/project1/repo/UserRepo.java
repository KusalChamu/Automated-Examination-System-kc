package com.pankaja.project1.repo;


import com.pankaja.project1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{

    User findByUserName(String username);

}
