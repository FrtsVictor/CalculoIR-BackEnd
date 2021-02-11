package com.alterdata.calculo.irpf.models.account;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Size(min = 3, max = 80)
    @Schema(description = "Nome referente ao usuario", example = "New Usuario", required = true)
    private String nome;

    @NotEmpty
    @Size(min = 5, max = 50)
    @Schema(description = "Senha para acesso", example = "senha123", required = true)
    private String senha;

    @NotEmpty
    @Size(min = 5, max = 30)
    @Schema(description = "Usuario para acesso", example = "Username", required = true)
    private String usuario;

}