package lt.viko.eif.denis.kladijev.marshall.service;

import lt.viko.eif.denis.kladijev.marshall.model.Game;
import lt.viko.eif.denis.kladijev.marshall.model.Player;
import lt.viko.eif.denis.kladijev.marshall.repository.GameRepository;
import lt.viko.eif.denis.kladijev.marshall.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link GameService}.
 */
public class GameServiceTest
{
    @Mock private GameRepository gameRepository;
    @Mock private PlayerRepository playerRepository;
    @InjectMocks private GameService gameService;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test that getAll returns all games.
     */
    @Test
    public void testGetAllGames()
    {
        Game game1 = new Game("Title1", "Genre1", "Desc1", null, null, null);
        Game game2 = new Game("Title2", "Genre2", "Desc2", null, null, null);
        when(gameRepository.findAll()).thenReturn(Arrays.asList(game1, game2));

        List<Game> games = gameService.getAll();
        assertThat(games).hasSize(2);
    }

    /**
     * Test that getById returns a game when found.
     */
    @Test
    public void testGetById_Found()
    {
        Game game = new Game("Title", "Genre", "Desc", null, null, null);
        game.setId(1L);
        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        Optional<Game> result = gameService.getById(1L);
        assertThat(result).isPresent();
        assertThat(result.get().getGameTitle()).isEqualTo("Title");
    }

    /**
     * Test that createGameForPlayer throws an exception when the player is not found.
     */
    @Test
    public void testCreateGameForPlayer_PlayerNotFound()
    {
        when(playerRepository.findById(1L)).thenReturn(Optional.empty());
        Game game = new Game("Title", "Genre", "Desc", null, null, null);

        Exception exception = assertThrows(RuntimeException.class, () -> gameService.createGameForPlayer(1L, game));
        assertThat(exception.getMessage()).contains("Player not found with id");
    }

    /**
     * Test that createGameForPlayer assigns a player and saves the game.
     */
    @Test
    public void testCreateGameForPlayer_Success()
    {
        Player player = new Player("GamePlayer", "gp@example.com", 3, 300);
        player.setId(1L);
        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));
        when(gameRepository.save(any(Game.class))).thenAnswer(invocation -> {
            Game savedGame = invocation.getArgument(0);
            savedGame.setId(10L);
            return savedGame;
        });

        Game game = new Game("Title", "Genre", "Desc", null, null, null);
        Game createdGame = gameService.createGameForPlayer(1L, game);

        assertThat(createdGame.getId()).isEqualTo(10L);
        assertThat(createdGame.getPlayer()).isEqualTo(player);
    }
}
