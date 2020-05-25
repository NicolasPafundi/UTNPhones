package com.utn.TPFinal.repositories;

import com.utn.TPFinal.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM users WHERE username= ?1 AND password = ?2", nativeQuery = true)
    User getByUserNameAndPassword(String userName, String password);

    @Query(value = "SELECT * FROM users WHERE usertype_id= ?1", nativeQuery = true)
    List<User> getAllByUserType(Integer userTypeId);
}
