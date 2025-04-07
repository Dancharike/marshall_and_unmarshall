package lt.viko.eif.denis.kladijev.marshall.repository;

import lt.viko.eif.denis.kladijev.marshall.model.Game;
import lt.viko.eif.denis.kladijev.marshall.model.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link GameRepository}.
 */
@DataJpaTest
public class GameRepositoryTest
{
    @Autowired private GameRepository gameRepository;
    @Autowired private PlayerRepository playerRepository;

    /**
     * Tests that a Game can be saved and then found by the Player's ID.
     */
    @Test
    public void testSaveAndFindGameByPlayerId()
    {
        Player player = new Player("GamePlayer", "gameplayer@example.com", 3, 300);
        player = playerRepository.save(player);

        Game game = new Game("Game Title", "Genre", "Description", player, null, null);
        game = gameRepository.save(game);

        List<Game> games = gameRepository.findByPlayerId(player.getId());
        assertThat(games).isNotEmpty();
        assertThat(games.get(0).getGameTitle()).isEqualTo("Game Title");
    }
}
