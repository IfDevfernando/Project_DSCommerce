package com.donbusiness.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donbusiness.business.DTO.UserDTO;
import com.donbusiness.business.entities.Role;
import com.donbusiness.business.entities.User;
import com.donbusiness.business.projections.UserDetailsProjection;
import com.donbusiness.business.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
		
		if(result.size() == 0) {
			throw new UsernameNotFoundException("user not found");
		}
		User user = new User();
		user.setEmail(username);
		user.setPassword(result.get(0).getPassword());
		
		//role list user
		for(UserDetailsProjection projection : result) {
			user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
		}
		return user;
	}
	//return login person
	protected User autheticated() {
		try {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
		String username = jwtPrincipal.getClaim("username");
		

		return  repository.findByEmail(username).get();
		}
		catch(Exception e) {
			throw new UsernameNotFoundException("Email not found");
		}
		
	}
	
	@Transactional(readOnly = true)
	public UserDTO getMe() {
		User user = autheticated();
		
		return new UserDTO(user);
	}

}