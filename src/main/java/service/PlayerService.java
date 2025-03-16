package service;

import model.Player;
import org.springframework.stereotype.Service;
import repository.PlayerRepository;

import java.util.List;

@Service
public class PlayerService
{
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository)
    {
        this.playerRepository = playerRepository;
    }

    public List<Player> GetAllPlayers()
    {
        return playerRepository.findAll();
    }

    public Player GetPlayerById(Long id)
    {
        return playerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player not found with id: " + id));
    }

    public Player CreatePlayer(Player player)
    {
        return playerRepository.save(player);
    }

    public Player UpdatePlayer(Long id, Player updatedPlayer)
    {
        Player existingPlayer = GetPlayerById(id);
        existingPlayer.setNickName(updatedPlayer.getNickName());
        existingPlayer.setLevel(updatedPlayer.getLevel());
        existingPlayer.setExperience(updatedPlayer.getExperience());
        existingPlayer.setAchievements(updatedPlayer.getAchievements());
        existingPlayer.setInventory(updatedPlayer.getInventory());

        return playerRepository.save(existingPlayer);
    }

    public void DeletePlayer(Long Id)
    {
        GetPlayerById(Id);
        playerRepository.deleteById(Id);
    }

    public Player FindByNickName(String nickname)
    {
        return playerRepository.findByNickName(nickname).orElseThrow(() -> new RuntimeException("Player not found with nickname: " + nickname));
    }
}
