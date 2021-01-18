package com.alterdata.calculo.irpf.models.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @NotEmpty
    @Size(min = 5, max = 30)
    private String username;
    @NotEmpty
    @Size(min = 5, max = 50)
    private String password;
    @NotEmpty
    @Size(min = 3, max = 80)
    private String nome;

}