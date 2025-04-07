package lt.viko.eif.denis.kladijev.marshall.repository;

import lt.viko.eif.denis.kladijev.marshall.model.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link PlayerRepository}.
 */
@DataJpaTest
public class PlayerRepositoryTest
{
    @Autowired private PlayerRepository playerRepository;

    /**
     * Tests that a Player can be saved and then found by its ID.
     */
    @Test
    public void testSaveAndFindPlayer()
    {
        Player player = new Player("JohnDoe", "john@example.com", 1, 100);
        player = playerRepository.save(player);

        Optional<Player> found = playerRepository.findById(player.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getNickName()).isEqualTo("JohnDoe");
    }

    /**
     * Tests that a Player can be found by its nickname.
     */
    @Test
    public void testFindByNickName()
    {
        Player player = new Player("UniqueNick", "unique@example.com", 2, 200);
        playerRepository.save(player);
        
        Optional<Player> found = playerRepository.findByNickName("UniqueNick");
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("unique@example.com");
    }
}
