package com.alterdata.calculo.ir.CalculoIRPF.repositories;

import com.alterdata.calculo.ir.CalculoIRPF.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByNome(String nome);

    @Query("from users where id = ?1")
    User getById(Integer id);

}

