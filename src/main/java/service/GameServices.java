package service;

import model.Game;
import org.springframework.stereotype.Service;
import repository.GameRepository;

import java.util.List;

@Service
public class GameServices
{
    private final GameRepository gameRepository;

    public GameServices(GameRepository gameRepository)
    {
        this.gameRepository = gameRepository;
    }

    public List<Game> GetAllGames()
    {
        return gameRepository.findAll();
    }

    public Game CreateGame(Game game)
    {
        return gameRepository.save(game);
    }

    public void DeleteGame(Long id)
    {
        gameRepository.deleteById(id);
    }
}
