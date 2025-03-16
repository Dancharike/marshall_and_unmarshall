package repository;

import model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchievementRepository extends JpaRepository<Achievement, Long>
{
    List<Achievement> FindByPlayerId(Long playerId);
}
