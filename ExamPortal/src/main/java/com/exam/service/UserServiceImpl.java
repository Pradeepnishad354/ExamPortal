package com.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.entity.Role;
import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.helper.UserFoundException;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User createUser(User user, List<UserRole> userRoles) throws Exception {

		User username = userRepository.findByUsername(user.getUsername());

		if (username != null) {

			System.out.println("user alerady present there ");

			throw new UserFoundException();

		} else {

			// create user

			for (UserRole ur : userRoles) {

				roleRepository.save(ur.getRole());
			}

			user.getUserRole().addAll(userRoles);
			
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			username = this.userRepository.save(user);
		}

		return username;
	}

	
	@Override
	public User getUser(String username) {
		
		return userRepository.findByUsername(username);
	}

	
	
	@Override
	public void deleteUser(Long id) {
		
		userRepository.deleteById(id);
		
	}

}
