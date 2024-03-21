package practice.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.security.domain.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
