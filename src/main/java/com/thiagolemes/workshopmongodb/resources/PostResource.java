package com.thiagolemes.workshopmongodb.resources;

import com.thiagolemes.workshopmongodb.domain.Post;
import com.thiagolemes.workshopmongodb.domain.User;
import com.thiagolemes.workshopmongodb.dto.UserDTO;
import com.thiagolemes.workshopmongodb.services.PostService;
import com.thiagolemes.workshopmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}
