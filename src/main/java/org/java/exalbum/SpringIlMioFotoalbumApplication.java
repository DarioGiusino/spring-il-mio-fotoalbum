package org.java.exalbum;

import org.java.exalbum.auth.pojo.Role;
import org.java.exalbum.auth.pojo.User;
import org.java.exalbum.auth.serv.RoleService;
import org.java.exalbum.auth.serv.UserService;
import org.java.exalbum.pojo.Category;
import org.java.exalbum.pojo.Photo;
import org.java.exalbum.serv.CategoryService;
import org.java.exalbum.serv.PhotoService;
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
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PhotoService photoService;

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
		
//		--------------------------------------------------------------------------------------------------------------------
		
		Category c1 = new Category("categoria 1");
		Category c2 = new Category("categoria 2");
		Category c3 = new Category("categoria 3");
		Category c4 = new Category("categoria 4");
		Category c5 = new Category("categoria 5");
		
		categoryService.save(c1);
		categoryService.save(c2);
		categoryService.save(c3);
		categoryService.save(c4);
		categoryService.save(c5);
		
//		--------------------------------------------------------------------------------------------------------------------
		
		Photo p1 = new Photo("foto 1", "descrizione foto 1", "https://picsum.photos/200/300", true, c3, c2);
		Photo p2 = new Photo("foto 2", "descrizione foto 2", "https://picsum.photos/200/300", false, c4, c5);
		Photo p3 = new Photo("foto 3", "descrizione foto 3", "https://picsum.photos/200/300", true, c5, c1);
		Photo p4 = new Photo("foto 4", "descrizione foto 4", "https://picsum.photos/200/300", false, c2, c3);
		Photo p5 = new Photo("foto 5", "descrizione foto 5", "https://picsum.photos/200/300", true, c1, c2);
		Photo p6 = new Photo("foto 6", "descrizione foto 6", "https://picsum.photos/200/300", false, c3);
		Photo p7 = new Photo("foto 7", "descrizione foto 7", "https://picsum.photos/200/300", true);
		
		photoService.save(p1);
		photoService.save(p2);
		photoService.save(p3);
		photoService.save(p4);
		photoService.save(p5);
		photoService.save(p6);
		photoService.save(p7);

	}

}
