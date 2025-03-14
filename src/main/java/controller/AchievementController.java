package controller;

import model.Achievement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AchievementService;

import java.util.List;

@RestController
@RequestMapping("/api/achievements")
public class AchievementController
{
    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService)
    {
        this.achievementService = achievementService;
    }

    @GetMapping
    public ResponseEntity<List<Achievement>> getAllAchievements()
    {
        return ResponseEntity.ok(achievementService.GetAllAchievements());
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<Achievement>> getAchievementsByPlayerId(@PathVariable Long playerId)
    {
        return ResponseEntity.ok(achievementService.GetAchievementsByPlayerId(playerId));
    }

    @PostMapping
    public ResponseEntity<Achievement> createAchievement(@RequestBody Achievement achievement)
    {
        return ResponseEntity.ok(achievementService.CreateAchievement(achievement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAchievement(@PathVariable Long id)
    {
        achievementService.DeleteAchievement(id);
        return ResponseEntity.noContent().build();
    }
}
