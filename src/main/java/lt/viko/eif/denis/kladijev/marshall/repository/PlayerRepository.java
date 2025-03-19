package lt.viko.eif.denis.kladijev.marshall.repository;

import lt.viko.eif.denis.kladijev.marshall.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long>
{
    Optional<Player> findByNickName(String nickname);
}
