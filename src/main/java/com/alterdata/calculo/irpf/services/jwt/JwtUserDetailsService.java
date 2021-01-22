package com.alterdata.calculo.irpf.services.jwt;

import com.alterdata.calculo.irpf.models.account.Usuario;
import com.alterdata.calculo.irpf.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Usuario user = userService.buscarPorUsernameOuLancarBadRequestException(username);
        return new org.springframework.security.core.userdetails.User(user.getUsuario(), user.getSenha(),
                new ArrayList<>());
    }
}