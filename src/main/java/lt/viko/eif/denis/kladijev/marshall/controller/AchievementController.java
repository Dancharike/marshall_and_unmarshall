package lt.viko.eif.denis.kladijev.marshall.controller;

import lt.viko.eif.denis.kladijev.marshall.model.Achievement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lt.viko.eif.denis.kladijev.marshall.service.AchievementService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/achievements")
public class AchievementController
{
    private final AchievementService service;

    public AchievementController(AchievementService service)
    {
        this.service = service;
    }

    @GetMapping
    public List<Achievement> getAllAchievements()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Achievement> getAchievementById(@PathVariable Long id)
    {
        Optional<Achievement> achievement = service.getById(id);
        return achievement.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Achievement createAchievement(@RequestBody Achievement achievement)
    {
        return service.save(achievement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAchievement(@PathVariable Long id)
    {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/player/{playerId}")
    public List<Achievement> getAchievementByPlayerId(@PathVariable Long playerId)
    {
        return service.getByPlayerId(playerId);
    }

    @GetMapping("/game/{gameId}")
    public List<Achievement> getAchievementByGameId(@PathVariable Long gameId)
    {
        return service.getByGameId(gameId);
    }
}
