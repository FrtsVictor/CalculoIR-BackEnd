package com.alterdata.calculo.irpf.models.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Size(min = 5, max = 50)
    @Schema(description = "Usuario para acesso", example = "Username", required = true)
    private String username;

    @NotEmpty
    @JsonIgnore
    @Size(min = 5)
    @Schema(description = "Senha para acesso", example = "senha123", required = true)
    private String password;

    @NotEmpty
    @Size(min = 3, max = 80)
    @Schema(description = "Nome referente ao usuario", example = "New User", required = true)
    private String nome;

    @Schema(description = "Salario mensal bruto", example = "1500.80")
    private double salarioMensal;

    @Schema(description = "Quantidade de dependentes", example = "2")
    private double dependentes;

    @Schema(description = "Valor da pensao alimenticia", example = "150.00")
    private double pensaoAlimenticia;

}