package com.alterdata.calculo.irpf.util;

import com.alterdata.calculo.irpf.models.account.UsuarioPutRequest;

public class CriarUserPutRequest {

    public static UsuarioPutRequest createUserPutRequest() {
        return UsuarioPutRequest.builder()
                .nome("Updated Nome")
                .usuario("testUsuario")
                .salarioMensal(1500)
                .dependentes(1)
                .pensaoAlimenticia(150)
                .build();
    }

}
