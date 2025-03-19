package lt.viko.eif.denis.kladijev.marshall.controller;

import lt.viko.eif.denis.kladijev.marshall.model.Achievement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lt.viko.eif.denis.kladijev.marshall.service.AchievementService;

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
    public ResponseEntity<List<Achievement>> GetAllAchievements()
    {
        return ResponseEntity.ok(achievementService.GetAllAchievements());
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<Achievement>> GetAchievementsByPlayerId(@PathVariable Long playerId)
    {
        return ResponseEntity.ok(achievementService.GetAchievementsByPlayerId(playerId));
    }

    @PostMapping("/player/{playerId}")
    public ResponseEntity<Achievement> CreateAchievement(@PathVariable Long playerId, @RequestBody Achievement achievement)
    {
        Achievement created = achievementService.CreateAchievement(playerId, achievement);
        return ResponseEntity.status(201).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteAchievement(@PathVariable Long id)
    {
        achievementService.DeleteAchievement(id);
        return ResponseEntity.noContent().build();
    }
}
