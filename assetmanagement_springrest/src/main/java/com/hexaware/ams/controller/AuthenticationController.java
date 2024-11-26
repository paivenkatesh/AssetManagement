package com.hexaware.ams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.ams.config.JwtService;
import com.hexaware.ams.dto.AuthenticationRequest;
import com.hexaware.ams.dto.AuthenticationResponse;
import com.hexaware.ams.dto.RegisterRequest;
import com.hexaware.ams.entity.Employee;
import com.hexaware.ams.entity.Role;
import com.hexaware.ams.exception.ResourceAlreadyExistsException;
import com.hexaware.ams.exception.ResourceNotFoundException;
import com.hexaware.ams.repository.IEmployeeRepository;
import com.hexaware.ams.repository.IRoleRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private IEmployeeRepository employeeRepository;
    
    @Autowired
    private IRoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
    	// Check if email already exists
    	if (employeeRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResourceAlreadyExistsException("Email already registered");
        }
    	// verifying role
    	Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
    	Employee employee = new Employee();
        // Set employee properties from request
    	employee.setName(request.getName());
        employee.setGender(request.getGender());
        employee.setContactNumber(request.getContactNumber());
        employee.setAddress(request.getAddress());
        employee.setEmail(request.getEmail());
        employee.setPassword(passwordEncoder.encode(request.getPassword()));
        employee.setRole(role);

        employeeRepository.save(employee);
        
        String jwtToken = jwtService.generateToken(employee);
        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        
        Employee employee = employeeRepository.findByEmail(request.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(employee);
        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }
}