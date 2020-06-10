package com.thiagolemes.workshopmongodb.config;

import com.thiagolemes.workshopmongodb.domain.Post;
import com.thiagolemes.workshopmongodb.domain.User;
import com.thiagolemes.workshopmongodb.dto.AuthorDTO;
import com.thiagolemes.workshopmongodb.dto.CommentDTO;
import com.thiagolemes.workshopmongodb.repositories.PostRepository;
import com.thiagolemes.workshopmongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        // Insert User
        userRepository.deleteAll();
        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        // Insert Post
        postRepository.deleteAll();
        Post p1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post p2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));
        CommentDTO c3 = new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(bob));

        p1.getComment().addAll(Arrays.asList(c1, c2));
        p2.getComment().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(p1, p2));

        // Incluindo os post no usuário
        maria.getPosts().addAll(Arrays.asList(p1, p2));
        userRepository.save(maria);


    }
}
