package net.endrigo.delivery.server.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.endrigo.delivery.server.exception.DisabledAccountException;
import net.endrigo.delivery.server.model.User;
import net.endrigo.delivery.server.model.enumeration.UserStatusEnum;
import net.endrigo.delivery.server.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));
		if (user.getStatus().equals(UserStatusEnum.INACTIVE)) {
			throw new DisabledAccountException();
		}

		return UserDetailsImpl.build(user);
	}

}
