package lt.viko.eif.denis.kladijev.marshall.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Game} class.
 */
public class GameTest
{

    /**
     * Tests the getters and setters of the Game class.
     */
    @Test
    public void testGameGettersAndSetters()
    {
        Player player = new Player("JaneDoe", "jane.doe@example.com", 5, 300);
        Game game = new Game();

        game.setId(1L);
        game.setGameTitle("Test Game");
        game.setGameGenre("Adventure");
        game.setGameDescription("Test Description");
        game.setPlayer(player);

        assertEquals(1L, game.getId());
        assertEquals("Test Game", game.getGameTitle());
        assertEquals("Adventure", game.getGameGenre());
        assertEquals("Test Description", game.getGameDescription());
        assertEquals(player, game.getPlayer());
    }
}
