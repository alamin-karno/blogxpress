package com.alaminkarno.blogxpress;

import com.alaminkarno.blogxpress.config.AppConstants;
import com.alaminkarno.blogxpress.entities.Role;
import com.alaminkarno.blogxpress.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogXpressApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogXpressApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode("123456"));

		try{
			Role admnRole = new Role();
			admnRole.setId(AppConstants.ADMIN_USER);
			admnRole.setName("ROLE_ADMIN");

			Role normalRole = new Role();
			normalRole.setId(AppConstants.NORMAL_USER);
			normalRole.setName("ROLE_NORMAL");

			List<Role> roles = List.of(admnRole,normalRole);
			List<Role> savedRoles = this.roleRepository.saveAll(roles);

			savedRoles.forEach(role -> {
				System.out.println(role.getName());
			});

		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
