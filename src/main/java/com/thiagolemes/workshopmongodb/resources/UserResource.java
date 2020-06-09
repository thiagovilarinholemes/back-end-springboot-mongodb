package com.thiagolemes.workshopmongodb.resources;

import com.thiagolemes.workshopmongodb.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        User u1 = new User("1", "Maria", "email.maria");
        User u2 = new User("1", "João", "email.joão");
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(u1,u2));
        return ResponseEntity.ok().body(list);
    }
}
