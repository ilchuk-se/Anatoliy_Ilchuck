package com.epam.homework4.cinemawebapp.repository;

import com.epam.homework4.cinemawebapp.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.login = ?1 AND u.password = ?2")
    User findByLoginAndPassword(String login, String password);
}
