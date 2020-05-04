package com.utn.TPFinal.Repositories.Contracts;

import com.utn.TPFinal.Domain.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
}
