package com.alterdata.calculo.irpf.controllers;

import com.alterdata.calculo.irpf.models.account.User;
import com.alterdata.calculo.irpf.models.account.UserRequest;
import com.alterdata.calculo.irpf.models.jwt.JwtRequest;
import com.alterdata.calculo.irpf.models.jwt.JwtResponse;
import com.alterdata.calculo.irpf.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class JwtAuthenticationController {

//	final AuthenticationManager authenticationManager;

	final UserService userService;

	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponse> createAuthenticationToken(@Valid @RequestBody JwtRequest authenticationRequest) throws Exception {
		userService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		return ResponseEntity.ok(userService.generateToken(authenticationRequest));
	}

	@PostMapping("/register")
	public ResponseEntity<User> saveUser(@Valid @RequestBody UserRequest user) throws Exception {
		return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<Page<User>> getALl(Pageable pageable){
		return ResponseEntity.ok(userService.listALl(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Integer id){
		return ResponseEntity.ok(userService.findByIdOrThrowBadRequestException(id));
	}

	@GetMapping("/{username}")
	public ResponseEntity<User> findByIdUsername(@PathVariable String username){
		return ResponseEntity.ok(userService.findByUserNameOrThrowsBadRequestException(username));
	}

	@PutMapping
	public ResponseEntity<Void> updateUser(@RequestBody UserRequest user){
		userService.replace(user);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteUser(@RequestParam Integer id){
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}