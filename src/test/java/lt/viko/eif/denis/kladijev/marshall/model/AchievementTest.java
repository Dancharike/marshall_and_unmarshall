package lt.viko.eif.denis.kladijev.marshall.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Achievement} class.
 */
public class AchievementTest
{

    /**
     * Tests the getters and setters of the Achievement class.
     */
    @Test
    public void testAchievementGettersAndSetters()
    {
        Player player = new Player("JohnDoe", "john.doe@example.com", 10, 500);
        Game game = new Game("Adventure Game", "Adventure", "Exciting game", player, null, null);
        LocalDateTime now = LocalDateTime.now();

        Achievement achievement = new Achievement();
        achievement.setId(1L);
        achievement.setAchievementName("First Achievement");
        achievement.setAchievementDescription("Achieved something important");
        achievement.setDateAchieved(now);
        achievement.setPlayer(player);
        achievement.setGame(game);

        assertEquals(1L, achievement.getId());
        assertEquals("First Achievement", achievement.getAchievementName());
        assertEquals("Achieved something important", achievement.getAchievementDescription());
        assertEquals(now, achievement.getDateAchieved());
        assertEquals(player, achievement.getPlayer());
        assertEquals(game, achievement.getGame());
    }
}
