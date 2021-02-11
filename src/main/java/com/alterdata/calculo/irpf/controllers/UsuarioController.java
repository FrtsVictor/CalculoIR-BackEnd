package com.alterdata.calculo.irpf.controllers;

import com.alterdata.calculo.irpf.models.account.UserRequest;
import com.alterdata.calculo.irpf.models.account.Usuario;
import com.alterdata.calculo.irpf.models.account.UsuarioPutRequest;
import com.alterdata.calculo.irpf.models.jwt.JwtRequest;
import com.alterdata.calculo.irpf.models.jwt.JwtResponse;
import com.alterdata.calculo.irpf.repositories.UserRepository;
import com.alterdata.calculo.irpf.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
public class UsuarioController {

    final UserService userService;
    final UserRepository repo;

    @PostMapping("/authenticate")
    @Operation(summary = "Autenticacao do usuario", description = "Autenticacao do usuario via username e senha")
    public ResponseEntity<JwtResponse> gerarToken(
            @Valid @RequestBody JwtRequest authenticationRequest) {
        userService.autenticar(authenticationRequest.getUsuario(), authenticationRequest.getSenha());
        return ResponseEntity.ok(userService.gerarToken(authenticationRequest));
    }

    @PostMapping("/register")
    @Operation(summary = "Cricao de usuario", description = "Criacao de usuario com nome, username e password")
    public ResponseEntity<Usuario> salvarUsuario(@Valid @RequestBody UserRequest user) {
        return new ResponseEntity<>(userService.salvar(user), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    @Operation(summary = "Listar todos usuarios", description = "Retorna lista paginada de usuarios")
    public ResponseEntity<Page<Usuario>> listarTodos(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(userService.listarTodos(pageable));
    }

    @GetMapping("/users/id/{id}")
    @Operation(summary = "Exibir usuario por id", description = "Retorna usuario por referencia de ID")
    public ResponseEntity<Usuario> encontrarId(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.buscarPorIdOuLancarBadRequestException(id));
    }

    @GetMapping("/users/username/{username}")
    @Operation(summary = "Exibir por usuario", description = "Retorna usuario por referencia de usuario")
    public ResponseEntity<Usuario> encontrarPorUsuario(@PathVariable String username) {
        return ResponseEntity.ok(userService.buscarPorUsernameOuLancarBadRequestException(username));
    }

    @PutMapping("/users")
    @Operation(summary = "Atualizar usuario por username", description = "Atualiza usuario por referencia de username")
    public ResponseEntity<Void> atualizarUsuario(@Valid @RequestBody UsuarioPutRequest user) {
        userService.atualizar(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Remove usuario por id", description = "Remove usuario por referencia de id")
    public ResponseEntity<Void> removerUsuario(@PathVariable Integer id) {
        userService.remover(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}