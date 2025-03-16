package controller;

import model.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.GameService;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController
{
    private final GameService gameService;

    public GameController(GameService gameService)
    {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<Game>> GetAllGames()
    {
        return ResponseEntity.ok(gameService.GetAllGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> GetGameById(@PathVariable Long id)
    {
        Game game = gameService.GetGameById(id);
        return ResponseEntity.ok(game);
    }

    @PostMapping
    public ResponseEntity<Game> CreateGame(@RequestBody Game game)
    {
        Game created = gameService.CreateGame(game);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> UpdateGame(@PathVariable Long id, @RequestBody Game updatedGame)
    {
        Game game = gameService.UpdateGame(id, updatedGame);
        return ResponseEntity.ok(game);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteGame(@PathVariable Long id)
    {
        gameService.DeleteGame(id);
        return ResponseEntity.noContent().build();
    }
}
