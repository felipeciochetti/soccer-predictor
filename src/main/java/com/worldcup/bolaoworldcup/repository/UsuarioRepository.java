package com.worldcup.bolaoworldcup.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.worldcup.bolaoworldcup.model.Games;
import com.worldcup.bolaoworldcup.model.Team;
import com.worldcup.bolaoworldcup.model.Usuarios;


@CrossOrigin("http://localhost:4200")
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {


    Optional<Usuarios> findById(Long id);
    
    Optional<Usuarios> findByEmail(String email);
    
    List<Usuarios> findByEmailLike(String email);

	
}
