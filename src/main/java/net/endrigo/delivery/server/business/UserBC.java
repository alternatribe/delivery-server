package net.endrigo.delivery.server.business;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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

import net.endrigo.delivery.server.controller.command.ClientRequest;
import net.endrigo.delivery.server.controller.command.LoginRequest;
import net.endrigo.delivery.server.controller.command.SignupRequest;
import net.endrigo.delivery.server.controller.dto.ClientDTO;
import net.endrigo.delivery.server.controller.dto.UserDTO;
import net.endrigo.delivery.server.controller.exception.MessageResponse;
import net.endrigo.delivery.server.model.Address;
import net.endrigo.delivery.server.model.Client;
import net.endrigo.delivery.server.model.User;
import net.endrigo.delivery.server.model.enumeration.RoleEnum;
import net.endrigo.delivery.server.model.enumeration.UserStatusEnum;
import net.endrigo.delivery.server.repository.ClientRepository;
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
	ClientRepository clientRepository;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	public ResponseEntity<CredentialsJWT> login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		return ResponseEntity.ok(new CredentialsJWT(jwt));
	}

	public ResponseEntity<MessageResponse> createUser(SignupRequest signUpRequest) {
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
	
	public ResponseEntity<ClientDTO> obterClient(UUID id) {
		Client client = this.clientRepository.getById(id);
		return ResponseEntity.ok().body(new ClientDTO(client));
	}

	public ResponseEntity<UserDTO> obter(UUID id) {
		User user = this.userRepository.getById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}

	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> lista = this.userRepository.findAll().stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return ResponseEntity.ok().body(lista);
	}

	public void delete(UUID id) {
		User user = this.userRepository.getById(id);
		user.setStatus(UserStatusEnum.INACTIVE);
		userRepository.save(user);
	}

	@Transactional
	public void updateClient(ClientRequest userClient) {
		boolean changed = false;
		Client novoClient = this.clientRepository.getById(userClient.getId());
		Address address = new Address();

//		if (!StringUtils.isBlank(userUpdate.getEmail())) {
//			novoUser.setEmail(userUpdate.getEmail());
//			changed = true;
//		}
//		
//		if (!StringUtils.isBlank(userUpdate.getName())) {
//			novoUser.setName(userUpdate.getName());
//			changed = true;
//		}
		
		if (novoClient.getAddress() == null) {
			novoClient.setAddress(address);
		}
		
		if (!StringUtils.isBlank(userClient.getAddress().getStreet())) {
			novoClient.getAddress().setStreet(userClient.getAddress().getStreet());
			changed = true;
		}
		if (!StringUtils.isBlank(userClient.getAddress().getHouseNumber())) {
			novoClient.getAddress().setHouseNumber(userClient.getAddress().getHouseNumber());
			changed = true;
		}
		if (!StringUtils.isBlank(userClient.getAddress().getReference())) {
			novoClient.getAddress().setReference(userClient.getAddress().getReference());
			changed = true;
		}
		if (!StringUtils.isBlank(userClient.getAddress().getDistrict())) {
			novoClient.getAddress().setDistrict(userClient.getAddress().getDistrict());
			changed = true;
		}
		if (!StringUtils.isBlank(userClient.getAddress().getZip())) {
			novoClient.getAddress().setZip(userClient.getAddress().getZip());
			changed = true;
		}
		if (!StringUtils.isBlank(userClient.getAddress().getCity())) {
			novoClient.getAddress().setCity(userClient.getAddress().getCity());
			changed = true;
		}
		if (!StringUtils.isBlank(userClient.getAddress().getState())) {
			novoClient.getAddress().setState(userClient.getAddress().getState());
			changed = true;
		}

		if (!StringUtils.isBlank(userClient.getNewPassword())) {
			try {
				Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(novoClient.getEmail(), userClient.getPassword()));
				if (authentication.isAuthenticated()) {
					novoClient.setPassword(encoder.encode(userClient.getPassword()));
					changed = true;
				}
			} catch (BadCredentialsException e) {
				throw new BadCredentialsException(e.getMessage());
			}
		}
		if (changed) {
			userRepository.save(novoClient);
		}
	}

}
