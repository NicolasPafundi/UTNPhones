package com.utn.TPFinal.Repositories.Contracts;

import com.utn.TPFinal.Domain.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM users WHERE username= ?1 AND password = ?2", nativeQuery = true)
    User GetByUserNameAndPassword(String userName, String password);
}
