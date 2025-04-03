package lt.viko.eif.denis.kladijev.marshall.controller;

import lt.viko.eif.denis.kladijev.marshall.model.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lt.viko.eif.denis.kladijev.marshall.service.PlayerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/players")
public class PlayerController
{
    private final PlayerService services;

    public PlayerController(PlayerService services)
    {
        this.services = services;
    }

    @GetMapping
    public List<Player> getAllPlayers()
    {
        return services.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id)
    {
        Optional<Player> player = services.getById(id);
        return player.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Player createPlayer(@RequestBody Player player)
    {
        return services.save(player);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id)
    {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}
