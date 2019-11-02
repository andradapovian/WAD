package com.andrada.mountaineering.config.runner;

import com.andrada.mountaineering.users.roles.model.UserRole;
import com.andrada.mountaineering.users.roles.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineGeneratorAppStartRunner implements CommandLineRunner {

    private static final String ROLE_VIEWER = "ROLE_VIEWER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    private UserRoleRepository userRoleRepository;

    @Autowired
    public CommandLineGeneratorAppStartRunner(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createRolesIfNotExisting();
    }

    private void createRolesIfNotExisting() {
        if (!userRoleRepository.findRoleByName(ROLE_VIEWER).isPresent()) {
            UserRole viewerRole = generateUserRole(ROLE_VIEWER);
            userRoleRepository.save(viewerRole);
        }

        if (!userRoleRepository.findRoleByName(ROLE_ADMIN).isPresent()) {
            UserRole adminRole = generateUserRole(ROLE_ADMIN);
            userRoleRepository.save(adminRole);
        }
    }

    private UserRole generateUserRole(String userRole) {
        UserRole role = new UserRole();
        role.setId(0);
        role.setRole(userRole);
        return role;
    }
}
