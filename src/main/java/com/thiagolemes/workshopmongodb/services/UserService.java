package com.thiagolemes.workshopmongodb.services;

import com.thiagolemes.workshopmongodb.domain.User;
import com.thiagolemes.workshopmongodb.dto.UserDTO;
import com.thiagolemes.workshopmongodb.repositories.UserRepository;
import com.thiagolemes.workshopmongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User obj){
        return repository.insert(obj);

    }

    public void delete(String id){
        findById(id);
        repository.deleteById(id);
    }

    public User update(@RequestBody User obj){
        User newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO obj){
        return new User(obj.getId(), obj.getName(), obj.getEmail());
    }
}

