package org.java.exalbum;

import org.java.exalbum.auth.pojo.Role;
import org.java.exalbum.auth.pojo.User;
import org.java.exalbum.auth.serv.RoleService;
import org.java.exalbum.auth.serv.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Role adminRole = new Role("ADMIN");
		roleService.save(adminRole);

//		--------------------------------------------------------------------------------------------------------------------

		final String psw = new BCryptPasswordEncoder().encode("password");

		User userAdmin = new User("admin", psw, adminRole);
		userService.save(userAdmin);

	}

}
