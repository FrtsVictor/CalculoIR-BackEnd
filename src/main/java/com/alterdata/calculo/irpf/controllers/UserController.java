package com.alterdata.calculo.irpf.controllers;

import com.alterdata.calculo.irpf.models.account.User;
import com.alterdata.calculo.irpf.models.account.UserPutRequest;
import com.alterdata.calculo.irpf.models.account.UserRequest;
import com.alterdata.calculo.irpf.models.jwt.JwtRequest;
import com.alterdata.calculo.irpf.models.jwt.JwtResponse;
import com.alterdata.calculo.irpf.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(value = "*")
@RequiredArgsConstructor
@RequestMapping("/v1")
public class UserController {

    final UserService userService;

    @PostMapping("/authenticate")
    @Operation(summary = "Autenticacao do usuario", description = "Autenticacao do usuario via username e senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario autenticado com sucesso!"),
            @ApiResponse(responseCode = "401", description = "Login ou senha invalidos")
    })
    public ResponseEntity<JwtResponse> createAuthenticationToken(@Valid @RequestBody JwtRequest authenticationRequest)
            throws Exception {
        userService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        return ResponseEntity.ok(userService.generateToken(authenticationRequest));
    }

    @PostMapping("/register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Usuario inserido invalido")
    })
    @Operation(summary = "Cricao de usuario", description = "Criacao de usuario com nome, username e password")
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserRequest user) throws Exception {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    @Operation(summary = "Listar todos usuarios", description = "Retorna lista paginada de usuarios")
    public ResponseEntity<Page<User>> getALl(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(userService.listALl(pageable));
    }

    @GetMapping("/users/id/{id}")
    @Operation(summary = "Exibir usuario por id", description = "Retorna usuario por referencia de ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario encontrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Usuario nao encontrado")
    })
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping("/users/username/{username}")
    @Operation(summary = "Exibir por username", description = "Retorna usuario por referencia de username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuario nao encontrado")
    })
    public ResponseEntity<User> findByIdUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUserNameOrThrowsBadRequestException(username));
    }

    @PutMapping("/users")
    @Operation(summary = "Atualizar usuario por username", description = "Atualiza usuario por referencia de username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Removido com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Usuario nao encontrado")
    })
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UserPutRequest user) {
        userService.replace(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Remove usuario por id", description = "Remove usuario por referencia de id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Removido com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Usuario nao encontrado")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}