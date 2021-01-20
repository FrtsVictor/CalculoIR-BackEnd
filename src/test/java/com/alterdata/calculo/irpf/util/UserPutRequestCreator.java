package com.alterdata.calculo.irpf.util;

import com.alterdata.calculo.irpf.models.account.UserPutRequest;

public class UserPutRequestCreator {

    public static UserPutRequest createUserPutRequest() {
        return UserPutRequest.builder()
                .nome("Updated Nome")
                .username("testUsername")
                .salarioMensal(1500)
                .dependentes(1)
                .pensaoAlimenticia(150)
                .build();
    }

}
