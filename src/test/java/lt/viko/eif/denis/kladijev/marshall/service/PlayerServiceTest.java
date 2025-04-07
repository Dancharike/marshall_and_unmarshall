package lt.viko.eif.denis.kladijev.marshall.service;

import lt.viko.eif.denis.kladijev.marshall.model.Player;
import lt.viko.eif.denis.kladijev.marshall.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link PlayerService}.
 */
public class PlayerServiceTest
{
    @Mock private PlayerRepository playerRepository;
    @InjectMocks private PlayerService playerService;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test that getAll returns all players.
     */
    @Test
    public void testGetAllPlayers()
    {
        Player p1 = new Player("JohnDoe", "john@example.com", 1, 100);
        Player p2 = new Player("JaneDoe", "jane@example.com", 2, 200);
        when(playerRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        assertThat(playerService.getAll()).hasSize(2);
    }

    /**
     * Test that getById returns a player when found.
     */
    @Test
    public void testGetById_Found()
    {
        Player p = new Player("JohnDoe", "john@example.com", 1, 100);
        p.setId(1L);
        when(playerRepository.findById(1L)).thenReturn(Optional.of(p));

        Optional<Player> result = playerService.getById(1L);
        assertThat(result).isPresent();
        assertThat(result.get().getNickName()).isEqualTo("JohnDoe");
    }

    /**
     * Test that updatePlayer throws an exception when the player is not found.
     */
    @Test
    public void testUpdatePlayer_NotFound()
    {
        when(playerRepository.findById(1L)).thenReturn(Optional.empty());

        Player updateData = new Player("NewNick", "new@example.com", 3, 300);
        Exception exception = assertThrows(RuntimeException.class, () -> playerService.updatePlayer(1L, updateData));

        assertThat(exception.getMessage()).contains("Player not found with id");
    }

    /**
     * Test that patchPlayer only updates non-null and non-zero fields.
     */
    @Test
    public void testPatchPlayer()
    {
        Player existing = new Player("OldNick", "old@example.com", 1, 100);
        existing.setId(1L);
        when(playerRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(playerRepository.save(any(Player.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Player patchData = new Player();
        patchData.setNickName("PatchedNick");
        // level and experience remain unchanged because 0 passes
        Player updated = playerService.patchPlayer(1L, patchData);

        assertThat(updated.getNickName()).isEqualTo("PatchedNick");
        assertThat(updated.getEmail()).isEqualTo("old@example.com");
        assertThat(updated.getLevel()).isEqualTo(1);
        assertThat(updated.getExperience()).isEqualTo(100);
    }

    /**
     * Test that getByNickName returns the correct player.
     */
    @Test
    public void testGetByNickName()
    {
        Player p = new Player("UniqueNick", "unique@example.com", 2, 200);
        when(playerRepository.findByNickName("UniqueNick")).thenReturn(Optional.of(p));

        Optional<Player> result = playerService.getByNickName("UniqueNick");
        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("unique@example.com");
    }
}
