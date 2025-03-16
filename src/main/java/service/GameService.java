package service;

import model.Game;
import org.springframework.stereotype.Service;
import repository.GameRepository;

import java.util.List;

@Service
public class GameService
{
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository)
    {
        this.gameRepository = gameRepository;
    }

    public List<Game> GetAllGames()
    {
        return gameRepository.findAll();
    }

    public Game GetGameById(Long id)
    {
        return gameRepository.findById(id).orElseThrow(() -> new RuntimeException("Game not found with id: " + id));
    }

    public Game CreateGame(Game game)
    {
        return gameRepository.save(game);
    }

    public Game UpdateGame(Long id, Game updatedGame)
    {
        Game existingGame = GetGameById(id);
        existingGame.setTitle(updatedGame.getTitle());
        existingGame.setGenre(updatedGame.getGenre());

        return gameRepository.save(existingGame);
    }

    public void DeleteGame(Long id)
    {
        GetGameById(id);
        gameRepository.deleteById(id);
    }
}
