package com.example.books.Repository;

import com.example.books.Models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
}
