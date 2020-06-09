package com.thiagolemes.workshopmongodb.config;

import com.thiagolemes.workshopmongodb.domain.User;
import com.thiagolemes.workshopmongodb.repositories.UserRepository;
import com.thiagolemes.workshopmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        // Insert User
        userRepository.deleteAll();
        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria, alex, bob));

    }
}
