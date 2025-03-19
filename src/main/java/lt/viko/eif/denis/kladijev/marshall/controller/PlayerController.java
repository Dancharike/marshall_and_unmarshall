package lt.viko.eif.denis.kladijev.marshall.controller;

import lt.viko.eif.denis.kladijev.marshall.model.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lt.viko.eif.denis.kladijev.marshall.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController
{
    private final PlayerService playerServices;

    public PlayerController(PlayerService playerServices)
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
        Player player = playerServices.GetPlayerById(id);
        return ResponseEntity.ok(player);
    }

    @PostMapping
    public ResponseEntity<Player> CreatePlayer(@RequestBody Player player)
    {
        Player createdPlayer = playerServices.CreatePlayer(player);
        return ResponseEntity.status(201).body(createdPlayer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> UpdatePlayer(@PathVariable Long id, @RequestBody Player updatedPlayer)
    {
        Player player = playerServices.UpdatePlayer(id, updatedPlayer);
        return ResponseEntity.ok(player);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeletePlayer(@PathVariable Long id)
    {
        playerServices.DeletePlayer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Player> FindByNickName(@RequestParam String nickname)
    {
        Player player = playerServices.FindByNickName(nickname);
        return ResponseEntity.ok(player);
    }
}
