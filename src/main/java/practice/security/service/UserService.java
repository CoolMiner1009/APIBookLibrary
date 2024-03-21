package practice.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import practice.security.domain.model.Role;
import practice.security.domain.model.User;
import practice.security.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("A user with the same name already exists");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("A user with this email already exists");
        }

        return save(user);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User is not found"));

    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }
    public void changeUserRole(String username, Role newRole) {
        // Находим пользователя по его идентификатору
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));

        // Обновляем роль пользователя
        user.setRole(newRole);

        // Сохраняем изменения в базе данных
        repository.save(user);
    }
}
