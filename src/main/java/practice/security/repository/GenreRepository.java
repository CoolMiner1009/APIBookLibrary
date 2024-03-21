package practice.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.security.domain.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
