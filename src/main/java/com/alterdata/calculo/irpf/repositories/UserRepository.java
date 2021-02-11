package com.alterdata.calculo.irpf.repositories;

import com.alterdata.calculo.irpf.models.account.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {

    @Query(value ="SELECT * FROM users u WHERE u.usuario = :usuario", nativeQuery = true)
    Optional<Usuario> findByUsuario(@Param("usuario") String usuario);

}

