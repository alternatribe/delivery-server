package net.endrigo.delivery.server.business;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import net.endrigo.delivery.server.controller.command.LoginRequest;
import net.endrigo.delivery.server.controller.command.SignupRequest;
import net.endrigo.delivery.server.controller.command.UserRequest;
import net.endrigo.delivery.server.controller.exception.MessageResponse;
import net.endrigo.delivery.server.controller.query.UserQuery;
import net.endrigo.delivery.server.model.Client;
import net.endrigo.delivery.server.model.RoleEnum;
import net.endrigo.delivery.server.model.User;
import net.endrigo.delivery.server.model.UserStatusEnum;
import net.endrigo.delivery.server.repository.UserRepository;
import net.endrigo.delivery.server.security.jwt.CredentialsJWT;
import net.endrigo.delivery.server.security.jwt.JwtUtils;

@Component
public class UserBC {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	public ResponseEntity<?> login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		return ResponseEntity
				.ok(new CredentialsJWT(jwt));
	}

	public ResponseEntity<?> createUser(SignupRequest signUpRequest) {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		User user = new User(signUpRequest.getName(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		if (signUpRequest.getRole() == null) {
			user.setRole(RoleEnum.ROLE_CLIENT);
		} else {
			switch (signUpRequest.getRole()) {
			case "admin":
				user.setRole(RoleEnum.ROLE_ADMIN);
				user.setStatus(UserStatusEnum.ACTIVE);
				break;
			case "employee":
				user.setRole(RoleEnum.ROLE_EMPLOYEE);
				user.setStatus(UserStatusEnum.ACTIVE);
				break;
			default:
				user.setRole(RoleEnum.ROLE_CLIENT);
			}
		}
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	public boolean createClient(SignupRequest signUpRequest) {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new RuntimeException("Error: Email is already in use!");
		}

		Client user = new Client(signUpRequest.getName(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		user.setRole(RoleEnum.ROLE_CLIENT);
		userRepository.save(user);
		return true;
	}
	
	public ResponseEntity<?> obter(UUID id) {
		User cliente = this.userRepository.getById(id);
		return ResponseEntity.ok().body(new UserQuery(cliente));
	}
	
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok().body(this.userRepository.findAll());
	}

	public void delete(UUID id) {
		User cliente = this.userRepository.getById(id);
		cliente.setStatus(UserStatusEnum.INACTIVE);
		userRepository.save(cliente);
	}

	public void update(UserRequest userUpdate) {
		User novoUser = this.userRepository.getById(userUpdate.getId());
		
//		if (!userUpdate.getEmail().isEmpty()) {
//			novoUser.setEmail(userUpdate.getEmail());
//		}
//		
//		if (!userUpdate.getName().isEmpty()) {
//			novoUser.setName(userUpdate.getName());
//		}
		
		if (!StringUtils.isBlank(userUpdate.getNewPassword())) {
			try {				
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(novoUser.getEmail(), userUpdate.getPassword()));
				if (authentication.isAuthenticated()) {				
					novoUser.setPassword(encoder.encode(userUpdate.getPassword()));
				}
			} catch (BadCredentialsException e) {
				throw new BadCredentialsException(e.getMessage());
			}
		}
		userRepository.save(novoUser);
	}

}
