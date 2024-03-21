package practice.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.security.service.UserService;

@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
@Tag(name = "Examples", description = "Examples of requests with different access rights")
public class ExampleController {
    private final UserService service;

    @GetMapping
    @Operation(summary = "Available only to authorized users")
    public String example() {
        return "Hello, world!";
    }

    @GetMapping("/admin")
    @Operation(summary = "Available only to authorized users with the ADMIN role")
    @PreAuthorize("hasRole('ADMIN')")
    public String exampleAdmin() {
        return "Hello, admin!";
    }

    @GetMapping("/get-admin")
    @Operation(summary = "Get ADMIN role (for demo)")
    public void getAdmin() {
        service.getAdmin();
    }
}