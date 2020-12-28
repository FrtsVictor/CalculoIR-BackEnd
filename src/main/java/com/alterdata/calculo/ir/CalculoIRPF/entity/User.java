package com.alterdata.calculo.ir.CalculoIRPF.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity(name = "users")
@NoArgsConstructor
public class User extends BaseEntity {

    @NotBlank
    @Size(max = 100,min = 3)
    private String nome;

    @NotBlank
    @Size(max = 50, min = 5)
    private String password;

    @Email
    @Size(min = 5)
    private String email;

    @Size(min = 4 , max = 20)
    private String username;

}
