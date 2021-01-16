package com.alterdata.calculo.irpf.repositories;

import com.alterdata.calculo.irpf.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByNome(String nome);
    Optional<User> findByUsername(String username);

}

