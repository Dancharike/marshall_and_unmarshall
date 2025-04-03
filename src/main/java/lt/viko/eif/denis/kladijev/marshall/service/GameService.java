package lt.viko.eif.denis.kladijev.marshall.service;

import lt.viko.eif.denis.kladijev.marshall.model.Game;
import lt.viko.eif.denis.kladijev.marshall.model.Player;
import lt.viko.eif.denis.kladijev.marshall.repository.PlayerRepository;
import lt.viko.eif.denis.kladijev.marshall.service.abstraction.AbstractCrudService;
import org.springframework.stereotype.Service;
import lt.viko.eif.denis.kladijev.marshall.repository.GameRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GameService extends AbstractCrudService<Game, Long>
{
    private final GameRepository repository;
    private final PlayerRepository playerRepository;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository)
    {
        this.repository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Game> getAll()
    {
        return repository.findAll();
    }

    @Override
    public Optional<Game> getById(Long id)
    {
        return repository.findById(id);
    }

    @Override
    public Game save(Game game)
    {
        return repository.save(game);
    }

    @Override
    public void delete(Long id)
    {
        repository.deleteById(id);
    }

    /**
     * Get player by ID.
     * @param playerId ID itself.
     * @return player ID found by method.
     */
    public List<Game> getByPlayerId(Long playerId)
    {
        return repository.findByPlayerId(playerId);
    }

    public Game createGameForPlayer(Long playerId, Game game)
    {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player not found with id " + playerId));
        game.setPlayer(player);
        return repository.save(game);
    }
}
