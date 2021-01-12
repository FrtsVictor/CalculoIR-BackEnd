package com.alterdata.calculo.ir.CalculoIRPF.User;


import com.alterdata.calculo.ir.CalculoIRPF.models.User;
import com.alterdata.calculo.ir.CalculoIRPF.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.alterdata.calculo.ir.CalculoIRPF.prototypes.UserPrototype.completeTestUserModel;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
public class UserCrudTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void deveCriarUsuario() {
        User newUser = userRepository.save(completeTestUserModel());
        assertTrue(newUser.getId() > 0 );
        assertNotNull(newUser);
    }

    @Test
    public void deveRetornarUsuarioPorId() {
        Integer id = 1;
        User nreUsrt = userRepository.save(completeTestUserModel());

        User userFound = userRepository.getById(id);
        assertTrue(userFound.getId().equals(1));
        assertNotNull(userFound);
    }
    @Test
     public void deveRetornarUsuarioPorNome() {
        String nome = "Victor";
        userRepository.save(completeTestUserModel());
        User userFound = userRepository.findByNome(nome);
        assertTrue(userFound.getNome().equals(nome));
        assertNotNull(userFound);
    }



}
