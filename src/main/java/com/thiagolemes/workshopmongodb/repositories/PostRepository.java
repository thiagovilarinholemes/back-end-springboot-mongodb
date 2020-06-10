package com.thiagolemes.workshopmongodb.repositories;

import com.thiagolemes.workshopmongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    // Consulta por metodo
    List<Post> findByTitleContainingIgnoreCase(String text); // Busca por uma string no titulo do post. Sempre deve ser findBynome_a_ser_buscadoContaining

    // Consulta por JSON
    @Query("{ 'title': { $regex: ?0, $options: 'i' } }") // ?0 primeiro parametro do metodo abaixo, 'i' ignora maiusculas e minusculas
    List<Post> searchTitle(String text);
}
