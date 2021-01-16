package com.alterdata.calculo.irpf.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "users")
@Builder
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotEmpty
	@Size(min = 5 , max = 50)
	private String username;

	@NotEmpty
	@JsonIgnore
	@Size(min = 5, max = 50)
	private String password;

	@NotEmpty
	@Size(min = 3, max = 80)
	private String nome;

    public User() {
 	//
    }

}