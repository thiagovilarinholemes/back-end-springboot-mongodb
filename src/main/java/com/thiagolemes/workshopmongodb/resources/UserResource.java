package com.thiagolemes.workshopmongodb.resources;

import com.thiagolemes.workshopmongodb.domain.Post;
import com.thiagolemes.workshopmongodb.domain.User;
import com.thiagolemes.workshopmongodb.dto.UserDTO;
import com.thiagolemes.workshopmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
        User user = service.fromDTO(objDto);
        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest() // Pega o endereço do novo objeto inserido, no user
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build(); // Retorna uma resposta vazia com o código 201 com o cabeçalho com o endereço do novo objeto
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build(); // Retorna um código 204, não retorna nada, operação realizada com sucesso
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id){
        User user = service.fromDTO(objDto);
        user.setId(id);
        user = service.update(user);
        return ResponseEntity.noContent().build(); // Retorna um código 204, não retorna nada, operação realizada com sucesso
    }

    // Carregando os post relacionados com o usuário
    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj.getPosts());
    }
}
