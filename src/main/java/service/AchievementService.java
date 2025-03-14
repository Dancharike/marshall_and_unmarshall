package service;

import model.Achievement;
import org.springframework.stereotype.Service;
import repository.AchievementRepository;

import java.util.List;

@Service
public class AchievementService
{
    private final AchievementRepository achievementRepository;

    public AchievementService(AchievementRepository achievementRepository)
    {
        this.achievementRepository = achievementRepository;
    }

    public List<Achievement> GetAllAchievements()
    {
        return achievementRepository.findAll();
    }

    public List<Achievement> GetAchievementsByPlayerId(Long playerId)
    {
        return achievementRepository.FindByPlayerId(playerId);
    }

    public Achievement CreateAchievement(Achievement achievement)
    {
        return achievementRepository.save(achievement);
    }

    public void DeleteAchievement(Long id)
    {
        achievementRepository.deleteById(id);
    }
}
