package com.alterdata.calculo.ir.CalculoIRPF.services.user;

import com.alterdata.calculo.ir.CalculoIRPF.dto.UserDto;
import com.alterdata.calculo.ir.CalculoIRPF.exceptions.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class DefaultUserService {

    private void validateUserDto(UserDto userDto) throws ValidationException {
        if (isNull(userDto)) {
            throw new ValidationException("Object user is null");
        }
        if (isNull(userDto.getUserName()) || userDto.getUserName().isEmpty()) {
            throw new ValidationException("Login is empty");
        }
    }
}
