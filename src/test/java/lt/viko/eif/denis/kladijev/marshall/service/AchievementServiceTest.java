package lt.viko.eif.denis.kladijev.marshall.service;

import lt.viko.eif.denis.kladijev.marshall.model.Achievement;
import lt.viko.eif.denis.kladijev.marshall.model.Game;
import lt.viko.eif.denis.kladijev.marshall.model.Player;
import lt.viko.eif.denis.kladijev.marshall.repository.AchievementRepository;
import lt.viko.eif.denis.kladijev.marshall.repository.GameRepository;
import lt.viko.eif.denis.kladijev.marshall.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link AchievementService}.
 */
public class AchievementServiceTest
{
    @Mock private AchievementRepository achievementRepository;
    @Mock private PlayerRepository playerRepository;
    @Mock private GameRepository gameRepository;
    @InjectMocks private AchievementService achievementService;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test that getAll returns all achievements.
     */
    @Test
    public void testGetAllAchievements()
    {
        Achievement ach1 = new Achievement("Win", "Victory", LocalDateTime.now(), null, null);
        Achievement ach2 = new Achievement("Lose", "Defeat", LocalDateTime.now(), null, null);
        when(achievementRepository.findAll()).thenReturn(Arrays.asList(ach1, ach2));

        List<Achievement> achievements = achievementService.getAll();
        assertThat(achievements).hasSize(2);
    }

    /**
     * Test that save throws an exception when associated player is not found.
     */
    @Test
    public void testSave_Achievement_PlayerNotFound()
    {
        Achievement achievement = new Achievement("Win", "Victory", LocalDateTime.now(), new Player(), new Game());
        // return empty for player lookup
        when(playerRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> achievementService.save(achievement));
        assertThat(exception.getMessage()).contains("Player not found");
    }

    /**
     * Test that save assigns the found player and game and saves the achievement.
     */
    @Test
    public void testSave_Success()
    {
        Player player = new Player("Achiever", "achiever@example.com", 4, 400);
        player.setId(1L);
        Game game = new Game("Game Title", "Genre", "Desc", player, null, null);
        game.setId(1L);
        Achievement achievement = new Achievement("Win", "Victory", LocalDateTime.now(), new Player(), new Game());

        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));
        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));
        when(achievementRepository.save(any(Achievement.class))).thenAnswer(invocation -> {
            Achievement saved = invocation.getArgument(0);
            saved.setId(10L);
            return saved;
        });

        // set IDs in achievement's associated player and game
        achievement.setPlayer(player);
        achievement.setGame(game);
        Achievement savedAchievement = achievementService.save(achievement);
        assertThat(savedAchievement.getId()).isEqualTo(10L);
        assertThat(savedAchievement.getAchievementName()).isEqualTo("Win");
    }

    /**
     * Test that getByPlayerId returns achievements for a given player id.
     */
    @Test
    public void testGetByPlayerId()
    {
        Achievement ach = new Achievement("Quest", "Completed quest", LocalDateTime.now(), null, null);
        when(achievementRepository.findByPlayerId(1L)).thenReturn(Arrays.asList(ach));

        List<Achievement> achievements = achievementService.getByPlayerId(1L);
        assertThat(achievements).isNotEmpty();
    }

    /**
     * Test that getByGameId returns achievements for a given game id.
     */
    @Test
    public void testGetByGameId()
    {
        Achievement ach = new Achievement("Quest", "Completed quest", LocalDateTime.now(), null, null);
        when(achievementRepository.findByGameId(1L)).thenReturn(Arrays.asList(ach));

        List<Achievement> achievements = achievementService.getByGameId(1L);
        assertThat(achievements).isNotEmpty();
    }
}
