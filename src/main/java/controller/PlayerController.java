package controller;

import model.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PlayerServices;

import java.util.List;

@RestController
@RequestMapping("api/player")
public class PlayerController
{
    private final PlayerServices playerServices;

    public PlayerController(PlayerServices playerServices)
    {
        this.playerServices = playerServices;
    }

    @GetMapping
    public ResponseEntity<List<Player>> GetAllPlayers()
    {
        return ResponseEntity.ok(playerServices.GetAllPlayers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> GetPlayerById(@PathVariable Long id)
    {
        return playerServices.GetPlayerById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Player> CreatePlayer(@RequestBody Player player)
    {
        return ResponseEntity.ok(playerServices.CreatePlayer(player));
    }

    @DeleteMapping
    public ResponseEntity<Void> DeletePlayer(@PathVariable Long id)
    {
        playerServices.DeletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}
