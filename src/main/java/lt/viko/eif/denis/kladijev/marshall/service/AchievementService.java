package lt.viko.eif.denis.kladijev.marshall.service;

import lt.viko.eif.denis.kladijev.marshall.model.Achievement;
import lt.viko.eif.denis.kladijev.marshall.model.Game;
import lt.viko.eif.denis.kladijev.marshall.model.Player;
import lt.viko.eif.denis.kladijev.marshall.repository.GameRepository;
import lt.viko.eif.denis.kladijev.marshall.repository.PlayerRepository;
import lt.viko.eif.denis.kladijev.marshall.service.abstraction.AbstractCrudService;
import org.springframework.stereotype.Service;
import lt.viko.eif.denis.kladijev.marshall.repository.AchievementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AchievementService extends AbstractCrudService<Achievement, Long>
{
    private final AchievementRepository repository;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    public AchievementService(AchievementRepository achievementRepository, PlayerRepository playerRepository, GameRepository gameRepository)
    {
        this.repository = achievementRepository;
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Achievement> getAll()
    {
        return repository.findAll();
    }

    @Override
    public Optional<Achievement> getById(Long id)
    {
        return repository.findById(id);
    }

    @Override
    public Achievement save(Achievement achievement)
    {
        Player player = playerRepository.findById(achievement.getPlayer().getId()).orElseThrow(() -> new RuntimeException("Player not found"));
        Game game = gameRepository.findById(achievement.getGame().getId()).orElseThrow(() -> new RuntimeException("Game not found"));
        achievement.setPlayer(player);
        achievement.setGame(game);
        return repository.save(achievement);
    }

    @Override
    public void delete(Long id)
    {
        repository.deleteById(id);
    }

    /**
     * Extra method for getting achievement by playerId.
     * @param playerId ID itself.
     * @return returns playerId.
     */
    public List<Achievement> getByPlayerId(Long playerId)
    {
        return repository.findByPlayerId(playerId);
    }

    /**
     * Extra method for getting achievement by gameId.
     * @param gameId game ID itself.
     * @return returns gameId.
     */
    public List<Achievement> getByGameId(Long gameId)
    {
        return repository.findByGameId(gameId);
    }
}
