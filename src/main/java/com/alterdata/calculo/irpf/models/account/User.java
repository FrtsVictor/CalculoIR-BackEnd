package com.alterdata.calculo.irpf.models.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@NotEmpty(message = "username mandatory")
	@Size(min = 5 , max = 50)
	private String username;

	@NotEmpty
	@JsonIgnore
	@Size(min = 5)
	private String password;

	@NotEmpty
	@Size(min = 3, max = 80)
	private String nome;

	private double salarioMensal;

	private double dependentes;

	private double pensaoAlimenticia;

}