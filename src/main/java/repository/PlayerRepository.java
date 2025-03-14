package repository;

import model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long>
{
    Optional<Player> findByNickName(String nickname);
}
