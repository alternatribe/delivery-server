package net.endrigo.delivery.server.business;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import net.endrigo.delivery.server.controller.command.LoginRequest;
import net.endrigo.delivery.server.controller.command.SignupRequest;
import net.endrigo.delivery.server.controller.exception.MessageResponse;
import net.endrigo.delivery.server.model.Client;
import net.endrigo.delivery.server.model.RoleEnum;
import net.endrigo.delivery.server.model.User;
import net.endrigo.delivery.server.model.UserStatusEnum;
import net.endrigo.delivery.server.repository.UserRepository;
import net.endrigo.delivery.server.security.jwt.CredentialsJWT;
import net.endrigo.delivery.server.security.jwt.JwtUtils;
import net.endrigo.delivery.server.security.service.UserDetailsImpl;

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

	public ResponseEntity<?> login(@Valid LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		String role = userDetails.getAuthorities().stream().findFirst().get().getAuthority();

		return ResponseEntity
				.ok(new CredentialsJWT(jwt, userDetails.getId(), userDetails.getName(), userDetails.getEmail(), role));
	}

	public ResponseEntity<?> createUser(@Valid SignupRequest signUpRequest) {
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

	public ResponseEntity<?> createClient(@Valid SignupRequest signUpRequest) {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		Client user = new Client(signUpRequest.getName(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		user.setRole(RoleEnum.ROLE_CLIENT);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
