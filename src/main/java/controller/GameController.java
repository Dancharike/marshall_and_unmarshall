package controller;

import model.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.GameServices;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController
{
    private final GameServices gameService;

    public GameController(GameServices gameService)
    {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames()
    {
        return ResponseEntity.ok(gameService.GetAllGames());
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game)
    {
        return ResponseEntity.ok(gameService.CreateGame(game));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id)
    {
        gameService.DeleteGame(id);
        return ResponseEntity.noContent().build();
    }
}
