package com.alterdata.calculo.irpf.services.user;

import com.alterdata.calculo.irpf.config.JwtTokenUtil;
import com.alterdata.calculo.irpf.exceptions.BadRequestException;
import com.alterdata.calculo.irpf.exceptions.ValidationException;
import com.alterdata.calculo.irpf.models.account.Usuario;
import com.alterdata.calculo.irpf.models.account.UsuarioPutRequest;
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

import javax.validation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bcryptEncoder;
    private final JwtUserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();


    public void autenticar(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new BadRequestException("Usuario Inativo");
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Credenciais invalidas");
        }
    }

    public JwtResponse gerarToken(JwtRequest authenticationRequest) {
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsuario());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }


    public Page<Usuario> listarTodos(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Usuario buscarPorIdOuLancarBadRequestException(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Usuario nao encontrado"));
    }

    @Transactional
    public Usuario salvar(UserRequest user) {
        Optional<Usuario> userAlreadyRegistered = userRepository.findByUsuario(user.getUsuario());
        if (userAlreadyRegistered.isPresent()) {
            throw new BadRequestException("Usuario ja cadastrado");
        }
        return this.userRepository.save(salvarUsuarioComPasswordEncoder(user));
    }

    private Usuario salvarUsuarioComPasswordEncoder(UserRequest user) {
        return Usuario.builder()
                .nome(user.getNome())
                .usuario(user.getUsuario())
                .senha(bcryptEncoder.encode(user.getSenha()))
                .build();
    }

    public Usuario buscarPorUsernameOuLancarBadRequestException(String usuario) {
        return userRepository.findByUsuario(usuario)
                .orElseThrow(() -> new BadRequestException("Usuario nao encontrado"));
    }

    public void remover(Integer id) {
        userRepository.delete(buscarPorIdOuLancarBadRequestException(id));
    }

    public void atualizar(UsuarioPutRequest userRequest) {
        Usuario updateUser = buscarPorUsernameOuLancarBadRequestException(userRequest.getUsuario());

        updateUser.setNome(userRequest.getNome());
        updateUser.setUsuario(userRequest.getUsuario());
        updateUser.setSalarioMensal(userRequest.getSalarioMensal());
        updateUser.setDependentes(userRequest.getDependentes());

        userRepository.save(updateUser);
    }

    public Object validarResponse(Object entity) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(entity);

        if (!violations.isEmpty()) {
            Map<Path, String> error = new HashMap<>();

            for (ConstraintViolation<Object> violation : violations) {
                error.put(violation.getPropertyPath(), violation.getMessage());
            }
            throw new ValidationException(error);
        }
        return Object.class;
    }
}
