package lt.viko.eif.denis.kladijev.marshall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.viko.eif.denis.kladijev.marshall.model.Player;
import lt.viko.eif.denis.kladijev.marshall.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for {@link PlayerController}.
 */
@WebMvcTest(PlayerController.class)
public class PlayerControllerTest
{
    @Autowired private MockMvc mockMvc;
    @MockBean private PlayerService playerService;
    @Autowired private ObjectMapper objectMapper;

    /**
     * Tests the retrieval of all players.
     */
    @Test
    public void testGetAllPlayers() throws Exception {
        Player player1 = new Player("John", "john@example.com", 1, 100);
        player1.setId(1L);
        Player player2 = new Player("Jane", "jane@example.com", 2, 200);
        player2.setId(2L);
        Mockito.when(playerService.getAll()).thenReturn(Arrays.asList(player1, player2));

        mockMvc.perform(get("/api/players")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].nickName", is("John"))).andExpect(jsonPath("$[1].nickName", is("Jane")));
    }

    /**
     * Tests the retrieval of a player by a valid ID.
     */
    @Test
    public void testGetPlayerById_Found() throws Exception
    {
        Player player = new Player("John", "john@example.com", 1, 100);
        player.setId(1L);
        Mockito.when(playerService.getById(1L)).thenReturn(Optional.of(player));

        mockMvc.perform(get("/api/players/1")).andExpect(status().isOk()).andExpect(jsonPath("$.nickName", is("John")));
    }

    /**
     * Tests retrieval of a player by an invalid ID.
     */
    @Test
    public void testGetPlayerById_NotFound() throws Exception
    {
        Mockito.when(playerService.getById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/players/1")).andExpect(status().isNotFound());
    }

    /**
     * Tests creation of a new player.
     */
    @Test
    public void testCreatePlayer() throws Exception
    {
        Player player = new Player("John", "john@example.com", 1, 100);
        player.setId(1L);
        Mockito.when(playerService.save(Mockito.any(Player.class))).thenReturn(player);

        String jsonContent = objectMapper.writeValueAsString(player);
        mockMvc.perform(post("/api/players").contentType(MediaType.APPLICATION_JSON).content(jsonContent)).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.nickName", is("John")));
    }

    /**
     * Tests deletion of a player.
     */
    @Test
    public void testDeletePlayer() throws Exception
    {
        Mockito.doNothing().when(playerService).delete(1L);

        mockMvc.perform(delete("/api/players/1")).andExpect(status().isNoContent());
    }

    /**
     * Tests full update (PUT) of a player.
     */
    @Test
    public void testUpdatePlayer() throws Exception
    {
        Player updatedPlayer = new Player("Johnny", "johnny@example.com", 2, 150);
        updatedPlayer.setId(1L);
        Mockito.when(playerService.updatePlayer(Mockito.eq(1L), Mockito.any(Player.class))).thenReturn(updatedPlayer);

        String jsonContent = objectMapper.writeValueAsString(updatedPlayer);
        mockMvc.perform(put("/api/players/1").contentType(MediaType.APPLICATION_JSON).content(jsonContent)).andExpect(status().isOk()).andExpect(jsonPath("$.nickName", is("Johnny"))).andExpect(jsonPath("$.level", is(2)));
    }

    /**
     * Tests partial update (PATCH) of a player.
     */
    @Test
    public void testPatchPlayer() throws Exception
    {
        Player patchedPlayer = new Player("Johnny", null, 0, 0);
        patchedPlayer.setId(1L);
        Mockito.when(playerService.patchPlayer(Mockito.eq(1L), Mockito.any(Player.class))).thenReturn(patchedPlayer);

        String jsonContent = "{\"nickName\":\"Johnny\"}";
        mockMvc.perform(patch("/api/players/1").contentType(MediaType.APPLICATION_JSON).content(jsonContent)).andExpect(status().isOk()).andExpect(jsonPath("$.nickName", is("Johnny")));
    }

    /**
     * Tests search for a player by nickname.
     */
    @Test
    public void testGetPlayerByNickName() throws Exception
    {
        Player player = new Player("UniqueNick", "unique@example.com", 3, 300);
        player.setId(1L);
        Mockito.when(playerService.getByNickName("UniqueNick")).thenReturn(Optional.of(player));

        mockMvc.perform(get("/api/players/search").param("nickName", "UniqueNick")).andExpect(status().isOk()).andExpect(jsonPath("$.email", is("unique@example.com")));
    }
}
