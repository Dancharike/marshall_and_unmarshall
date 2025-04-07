package lt.viko.eif.denis.kladijev.marshall.repository;

import lt.viko.eif.denis.kladijev.marshall.model.Achievement;
import lt.viko.eif.denis.kladijev.marshall.model.Game;
import lt.viko.eif.denis.kladijev.marshall.model.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link AchievementRepository}.
 */
@DataJpaTest
public class AchievementRepositoryTest
{
    @Autowired private AchievementRepository achievementRepository;
    @Autowired private PlayerRepository playerRepository;
    @Autowired private GameRepository gameRepository;

    /**
     * Tests that an Achievement can be saved and then found by the Player's ID.
     */
    @Test
    public void testSaveAndFindAchievementByPlayerId()
    {
        Player player = new Player("Achiever", "achiever@example.com", 4, 400);
        player = playerRepository.save(player);

        Game game = new Game("Achievement Game", "Adventure", "Exciting game", player, null, null);
        game = gameRepository.save(game);

        Achievement achievement = new Achievement("Win", "Won the battle", LocalDateTime.now(), player, game);
        achievement = achievementRepository.save(achievement);

        List<Achievement> achievements = achievementRepository.findByPlayerId(player.getId());
        assertThat(achievements).isNotEmpty();
        assertThat(achievements.get(0).getAchievementName()).isEqualTo("Win");
    }

    /**
     * Tests that an Achievement can be found by the Game's ID.
     */
    @Test
    public void testFindAchievementByGameId()
    {
        Player player = new Player("GameAchiever", "gameachiever@example.com", 5, 500);
        player = playerRepository.save(player);

        Game game = new Game("Game For Achievement", "RPG", "Role playing game", player, null, null);
        game = gameRepository.save(game);

        Achievement achievement = new Achievement("Quest Completed", "Completed quest", LocalDateTime.now(), player, game);
        achievement = achievementRepository.save(achievement);

        List<Achievement> achievements = achievementRepository.findByGameId(game.getId());
        assertThat(achievements).isNotEmpty();
        assertThat(achievements.get(0).getAchievementDescription()).contains("Completed quest");
    }
}
