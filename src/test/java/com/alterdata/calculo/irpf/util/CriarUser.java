package com.alterdata.calculo.irpf.util;

import com.alterdata.calculo.irpf.models.account.Usuario;

public class CriarUser {

    public static Usuario usuarioParaSalvar() {
        return Usuario.builder()
                .nome("Test")
                .usuario("testUsuario")
                .senha("123321")
                .build();
    }

    public static Usuario usuarioSalvoValido() {
        return Usuario.builder()
                .nome("Test")
                .usuario("testUsuario")
                .senha("123321")
                .id(1)
                .build();
    }

}
