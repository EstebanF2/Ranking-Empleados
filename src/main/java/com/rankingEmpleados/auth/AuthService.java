package com.rankingEmpleados.auth;

import com.rankingEmpleados.jwt.JwtService;
import com.rankingEmpleados.model.Employee;
import com.rankingEmpleados.model.Role;
import com.rankingEmpleados.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user= employeeRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    //AQUI SE CREA EL OBJETO USUARIO Y SE OBTIENE EL TOKEN POR MEDIO DE JWT
    public AuthResponse register(RegisterRequest request) {
        Employee empleado = Employee.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .rol((Role.USER))
                .build();

        employeeRepository.save(empleado);

        return AuthResponse.builder()
                .token(jwtService.getToken(empleado))
                .build();
    }
}
