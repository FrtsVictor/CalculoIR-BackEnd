package com.alterdata.calculo.irpf.models.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPutRequest {

    @NotEmpty
    @Size(min = 5, max = 50)
    @Schema(description = "Usuario para acesso", example = "Username", required = true)
    private String usuario;

    @NotEmpty
    @Size(min = 3, max = 80)
    @Schema(description = "Nome referente ao usuario", example = "New Usuario", required = true)
    private String nome;

    @PositiveOrZero
    @Schema(description = "Salario mensal bruto referente ao usuario", example = "1100.00", required = true)
    private double salarioMensal;

    @PositiveOrZero
    @Schema(description = "Quantidade de dependentes", example = "2")
    private double dependentes;

    @PositiveOrZero
    @Schema(description = "Valor da pensao alimenticia", example = "150.00")
    private double pensaoAlimenticia;


}
