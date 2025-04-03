package lt.viko.eif.denis.kladijev.marshall.controller;

import lt.viko.eif.denis.kladijev.marshall.model.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lt.viko.eif.denis.kladijev.marshall.service.GameService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController
{
    private final GameService service;

    public GameController(GameService service)
    {
        this.service = service;
    }

    @GetMapping
    public List<Game> getAllGames()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id)
    {
        Optional<Game> game = service.getById(id);
        return game.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Game createGame(@RequestBody Game game)
    {
        return service.save(game);
    }

    @PostMapping("/player/{playerId}")
    public ResponseEntity<Game> createGameForPlayer(@PathVariable Long playerId, @RequestBody Game game)
    {
        Game createdGame = service.createGameForPlayer(playerId, game);
        return ResponseEntity.status(201).body(createdGame);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id)
    {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
