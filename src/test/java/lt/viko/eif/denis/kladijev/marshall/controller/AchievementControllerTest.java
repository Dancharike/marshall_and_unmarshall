package lt.viko.eif.denis.kladijev.marshall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.viko.eif.denis.kladijev.marshall.model.Achievement;
import lt.viko.eif.denis.kladijev.marshall.service.AchievementService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for {@link AchievementController}.
 */
@WebMvcTest(AchievementController.class)
public class AchievementControllerTest
{
    @Autowired private MockMvc mockMvc;
    @MockBean private AchievementService achievementService;
    @Autowired private ObjectMapper objectMapper;

    /**
     * Tests retrieval of all achievements.
     */
    @Test
    public void testGetAllAchievements() throws Exception
    {
        Achievement achievement1 = new Achievement("Win", "Won battle", LocalDateTime.now(), null, null);
        achievement1.setId(1L);
        Achievement achievement2 = new Achievement("Lose", "Lost battle", LocalDateTime.now(), null, null);
        achievement2.setId(2L);
        Mockito.when(achievementService.getAll()).thenReturn(Arrays.asList(achievement1, achievement2));

        mockMvc.perform(get("/api/achievements")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    /**
     * Tests retrieval of an achievement by a valid ID.
     */
    @Test
    public void testGetAchievementById_Found() throws Exception
    {
        Achievement achievement = new Achievement("Win", "Won battle", LocalDateTime.now(), null, null);
        achievement.setId(1L);
        Mockito.when(achievementService.getById(1L)).thenReturn(Optional.of(achievement));

        mockMvc.perform(get("/api/achievements/1")).andExpect(status().isOk()).andExpect(jsonPath("$.achievementName", is("Win")));
    }

    /**
     * Tests creation of a new achievement.
     */
    @Test
    public void testCreateAchievement() throws Exception
    {
        Achievement achievement = new Achievement("Win", "Won battle", LocalDateTime.now(), null, null);
        achievement.setId(1L);
        Mockito.when(achievementService.save(Mockito.any(Achievement.class))).thenReturn(achievement);

        String jsonContent = objectMapper.writeValueAsString(achievement);
        mockMvc.perform(post("/api/achievements").contentType(MediaType.APPLICATION_JSON).content(jsonContent)).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.achievementName", is("Win")));
    }

    /**
     * Tests deletion of an achievement by its ID.
     */
    @Test
    public void testDeleteAchievement() throws Exception
    {
        Mockito.doNothing().when(achievementService).delete(1L);
        mockMvc.perform(delete("/api/achievements/1")).andExpect(status().isNoContent());
    }

    /**
     * Tests retrieval of achievements by player ID.
     */
    @Test
    public void testGetAchievementByPlayerId() throws Exception
    {
        Achievement achievement = new Achievement("Win", "Won battle", LocalDateTime.now(), null, null);
        achievement.setId(1L);
        Mockito.when(achievementService.getByPlayerId(1L)).thenReturn(Arrays.asList(achievement));

        mockMvc.perform(get("/api/achievements/player/1")).andExpect(status().isOk()).andExpect(jsonPath("$[0].achievementName", is("Win")));
    }

    /**
     * Tests retrieval of achievements by game ID.
     */
    @Test
    public void testGetAchievementByGameId() throws Exception
    {
        Achievement achievement = new Achievement("Win", "Won battle", LocalDateTime.now(), null, null);
        achievement.setId(1L);
        Mockito.when(achievementService.getByGameId(1L)).thenReturn(Arrays.asList(achievement));

        mockMvc.perform(get("/api/achievements/game/1")).andExpect(status().isOk()).andExpect(jsonPath("$[0].achievementName", is("Win")));
    }
}
