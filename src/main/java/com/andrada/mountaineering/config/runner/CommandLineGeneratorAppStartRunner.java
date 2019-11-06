package com.andrada.mountaineering.config.runner;

import com.andrada.mountaineering.users.model.User;
import com.andrada.mountaineering.users.repository.UserRepository;
import com.andrada.mountaineering.users.roles.model.UserRole;
import com.andrada.mountaineering.users.roles.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CommandLineGeneratorAppStartRunner implements CommandLineRunner {

    private static final String ROLE_VIEWER = "ROLE_VIEWER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${spring.security.user.name}")
    private String adminName;

    @Value("${spring.security.user.password}")
    private String adminPassword;

    @Autowired
    public CommandLineGeneratorAppStartRunner(UserRoleRepository userRoleRepository,
                                              UserRepository userRepository,
                                              BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

   @Override
    public void run(String... args) throws Exception {
        createAdminIfNotExisting();
    }

    private void createAdminIfNotExisting() {
        if (userRepository.getAdminUserCount() == 0) {
            UserRole viewerRole = userRoleRepository.findRoleByName(ROLE_VIEWER).orElse(generateUserRole(ROLE_VIEWER));
            UserRole adminRole = userRoleRepository.findRoleByName(ROLE_ADMIN).orElse(generateUserRole(ROLE_ADMIN));
            userRepository.save(createAdminUser(Stream.of(viewerRole, adminRole).collect(Collectors.toSet())));
        }
    }

    private User createAdminUser(Set<UserRole> roles) {
        User admin = new User();
        admin.setId(0);
        admin.setUsername(adminName);
        admin.setPassword(bCryptPasswordEncoder.encode(adminPassword));
        admin.setEmail("admin@mountaineering.com");
        admin.setFirstName("Application");
        admin.setLastName("Admin");
        admin.setRoles(roles);
        return admin;
    }

    private UserRole generateUserRole(String userRole) {
        UserRole role = new UserRole();
        role.setId(0);
        role.setRole(userRole);
        return role;
    }
}
