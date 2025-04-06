package lt.viko.eif.denis.kladijev.marshall.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Player} class.
 */
public class PlayerTest
{

    /**
     * Tests the getters and setters of the Player class.
     */
    @Test
    public void testPlayerGettersAndSetters()
    {
        Player player = new Player("JohnDoe", "john.doe@example.com", 10, 500);
        player.setId(1L);

        assertEquals(1L, player.getId());
        assertEquals("JohnDoe", player.getNickName());
        assertEquals("john.doe@example.com", player.getEmail());
        assertEquals(10, player.getLevel());
        assertEquals(500, player.getExperience());
    }
}
