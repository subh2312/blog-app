package com.subhankar.blogappbackend;

import com.subhankar.blogappbackend.entities.Role;
import com.subhankar.blogappbackend.repositories.Rolerepository;
import com.subhankar.blogappbackend.utils.AppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
//public class BlogAppBackendApplication implements CommandLineRunner {
public class BlogAppBackendApplication {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private static Rolerepository rolerepository;
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    public static void main(String[] args) {
        SpringApplication.run(BlogAppBackendApplication.class, args);

        try {
            Role role = new Role();
            role.setId(AppConstants.ADMIN_ROLE);
            role.setName("ROLE_ADMIN");
            Role role1 = new Role();
            role1.setId(AppConstants.USER_ROLE);
            role1.setName("ROLE_USER");

            rolerepository.saveAll(List.of(role, role1));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println(this.passwordEncoder.encode("abc"));
//    }
}
