package service;

import model.Achievement;
import model.Player;
import org.springframework.stereotype.Service;
import repository.AchievementRepository;
import repository.PlayerRepository;

import java.util.List;

@Service
public class AchievementService
{
    private final AchievementRepository achievementRepository;
    private final PlayerRepository playerRepository;

    public AchievementService(AchievementRepository achievementRepository, PlayerRepository playerRepository)
    {
        this.achievementRepository = achievementRepository;
        this.playerRepository = playerRepository;
    }

    public List<Achievement> GetAllAchievements()
    {
        return achievementRepository.findAll();
    }

    public List<Achievement> GetAchievementsByPlayerId(Long playerId)
    {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player not found with id: " + playerId));
        return achievementRepository.FindByPlayerId(player.getId());
    }

    public Achievement CreateAchievement(Long playerId ,Achievement achievement)
    {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player not found with id: " + playerId));
        achievement.setPlayer(player);
        return achievementRepository.save(achievement);
    }

    public void DeleteAchievement(Long id)
    {
        Achievement achievement = achievementRepository.findById(id).orElseThrow(() -> new RuntimeException("Achievement not found with id: " + id));
        achievementRepository.delete(achievement);
    }
}
