package repository;

import model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long>
{
    Optional<Game> findByGenre(String genre);
    Optional<Game> findByTitle(String title);
}
