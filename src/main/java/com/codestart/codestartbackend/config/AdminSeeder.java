package com.codestart.codestartbackend.config;

import com.codestart.codestartbackend.entity.AdminUser;
import com.codestart.codestartbackend.repository.AdminUserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements ApplicationRunner {
    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;
    public AdminSeeder(AdminUserRepository adminUserRepository, PasswordEncoder passwordEncoder) {
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(adminUserRepository.count() > 0){
            return;
        }
        AdminUser admin = new AdminUser();
        admin.setUsername("admin");
        admin.setPasswordHash(passwordEncoder.encode("ChangeMe123"));
        adminUserRepository.save(admin);
        System.out.println("Default Admin created : username 'admin' . Change the password after first login ." );
    }
}
