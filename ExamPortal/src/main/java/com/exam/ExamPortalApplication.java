package com.exam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.helper.UserFoundException;
import com.exam.service.UserService;

@SpringBootApplication
public class ExamPortalApplication implements CommandLineRunner{

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ExamPortalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
//		try {
//		User user=new User();
//		
//		user.setFirstName("pradeep");
//		user.setLastName("nishad");
//         user.setPassword(bCryptPasswordEncoder.encode("12345"));
//		user.setPhone("9834128244");
//	
//		user.setUsername("Pradeep@354");
//		user.setEmail("pradeepnishad354@gmail.com");
//		
//		user.setProfile("welocme.jpeg");
//		
//		
//		Role role1=new Role();
//		
//		role1.setRoleId(10L);
//		role1.setRoleName("ADMIN");
//		
//		List<UserRole> userRoleList=new ArrayList<>();
//		UserRole userRole=new UserRole();
//		
//		userRole.setRole(role1);
//		
//		userRole.setUser(user);
//		userRoleList.add(userRole);
//		
//		User user2 = this.userService.createUser(user, userRoleList);
//		
//		System.out.println(user2.getUsername());
//		
//	}catch(UserFoundException exception) {
//		
//		exception.printStackTrace();	
//	}
		
		
		BCryptPasswordEncoder b=new BCryptPasswordEncoder();
		String encode = b.encode("12345");
		System.out.println(encode);
	}
	
		



}
