package practice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import practice.security.domain.model.Role;
import practice.security.service.UserService;

import java.util.Scanner;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = String.valueOf(scanner.nextLine());

        System.out.print("Enter a new user role (ROLE_USER or ROLE_ADMIN): ");
        Role newRole = Role.valueOf(scanner.nextLine());

        userService.changeUserRole(username, newRole);

        System.out.println("The user's role has been successfully changed!");
    }
}