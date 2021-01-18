package com.alterdata.calculo.irpf.services.user;

import com.alterdata.calculo.irpf.config.JwtTokenUtil;
import com.alterdata.calculo.irpf.exceptions.BadRequestException;
import com.alterdata.calculo.irpf.models.account.User;
import com.alterdata.calculo.irpf.models.account.UserPutRequest;
import com.alterdata.calculo.irpf.models.account.UserRequest;
import com.alterdata.calculo.irpf.models.jwt.JwtRequest;
import com.alterdata.calculo.irpf.models.jwt.JwtResponse;
import com.alterdata.calculo.irpf.repositories.UserRepository;
import com.alterdata.calculo.irpf.services.jwt.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bcryptEncoder;
    private final JwtUserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    public Page<User> listALl(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public JwtResponse generateToken(JwtRequest authenticationRequest) {
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }

    public User findByIdOrThrowBadRequestException(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }

    @Transactional
    public User save(UserRequest user) {
        Optional<User> userAlreadyRegistered = userRepository.findByUsername(user.getUsername());
        if (userAlreadyRegistered.isPresent()) {
            throw new BadRequestException("User already registered");
        }
        return this.userRepository.save(saveUserRequestToUserWithPassEncoder(user));
    }

    private User saveUserRequestToUserWithPassEncoder(UserRequest user) {
        return User.builder()
                .nome(user.getNome())
                .username(user.getUsername())
                .password(bcryptEncoder.encode(user.getPassword()))
                .build();
    }

    public User findByUserNameOrThrowsBadRequestException(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }

    public void delete(Integer id) {
        userRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(UserPutRequest userRequest) {
        User updateUser = findByUserNameOrThrowsBadRequestException(userRequest.getUsername());
        updateUser.setNome(userRequest.getNome());
        updateUser.setUsername(userRequest.getUsername());
        updateUser.setSalarioMensal(userRequest.getSalarioMensal());
        updateUser.setDependentes(userRequest.getDependentes());

        userRepository.save(updateUser);
    }

}
