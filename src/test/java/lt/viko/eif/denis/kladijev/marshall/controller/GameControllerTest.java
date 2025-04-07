package lt.viko.eif.denis.kladijev.marshall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.viko.eif.denis.kladijev.marshall.model.Game;
import lt.viko.eif.denis.kladijev.marshall.model.Player;
import lt.viko.eif.denis.kladijev.marshall.service.GameService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for {@link GameController}.
 */
@WebMvcTest(GameController.class)
public class GameControllerTest
{
    @Autowired private MockMvc mockMvc;
    @MockBean private GameService gameService;
    @Autowired private ObjectMapper objectMapper;

    /**
     * Tests retrieval of all games.
     */
    @Test
    public void testGetAllGames() throws Exception
    {
        Game game1 = new Game("Title1", "Genre1", "Desc1", null, null, null);
        game1.setId(1L);
        Game game2 = new Game("Title2", "Genre2", "Desc2", null, null, null);
        game2.setId(2L);
        Mockito.when(gameService.getAll()).thenReturn(Arrays.asList(game1, game2));

        mockMvc.perform(get("/api/games")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].gameTitle", is("Title1"))).andExpect(jsonPath("$[1].gameTitle", is("Title2")));
    }

    /**
     * Tests retrieval of a game by a valid ID.
     */
    @Test
    public void testGetGameById_Found() throws Exception
    {
        Game game = new Game("Title1", "Genre1", "Desc1", null, null, null);
        game.setId(1L);
        Mockito.when(gameService.getById(1L)).thenReturn(Optional.of(game));

        mockMvc.perform(get("/api/games/1")).andExpect(status().isOk()).andExpect(jsonPath("$.gameTitle", is("Title1")));
    }

    /**
     * Tests creation of a new game.
     */
    @Test
    public void testCreateGame() throws Exception
    {
        Game game = new Game("Title1", "Genre1", "Desc1", null, null, null);
        game.setId(1L);
        Mockito.when(gameService.save(Mockito.any(Game.class))).thenReturn(game);

        String jsonContent = objectMapper.writeValueAsString(game);
        mockMvc.perform(post("/api/games").contentType(MediaType.APPLICATION_JSON).content(jsonContent)).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.gameTitle", is("Title1")));
    }

    /**
     * Tests creation of a new game for a specific player.
     */
    @Test
    public void testCreateGameForPlayer() throws Exception
    {
        Player player = new Player("John", "john@example.com", 1, 100);
        player.setId(1L);
        Game game = new Game("Title1", "Genre1", "Desc1", player, null, null);
        game.setId(1L);
        Mockito.when(gameService.createGameForPlayer(Mockito.eq(1L), Mockito.any(Game.class))).thenReturn(game);

        String jsonContent = objectMapper.writeValueAsString(game);
        mockMvc.perform(post("/api/games/player/1").contentType(MediaType.APPLICATION_JSON).content(jsonContent)).andExpect(status().isCreated()).andExpect(jsonPath("$.gameTitle", is("Title1")));
    }

    /**
     * Tests deletion of a game by its ID.
     */
    @Test
    public void testDeleteGame() throws Exception
    {
        Mockito.doNothing().when(gameService).delete(1L);
        mockMvc.perform(delete("/api/games/1")).andExpect(status().isNoContent());
    }
}
