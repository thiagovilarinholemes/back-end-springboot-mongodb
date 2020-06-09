package com.thiagolemes.workshopmongodb.services;

import com.thiagolemes.workshopmongodb.domain.User;
import com.thiagolemes.workshopmongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }
}

