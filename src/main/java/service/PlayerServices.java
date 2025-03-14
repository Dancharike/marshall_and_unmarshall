package service;

import model.Player;
import org.springframework.stereotype.Service;
import repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServices
{
    private final PlayerRepository playerRepository;

    public PlayerServices(PlayerRepository playerRepository)
    {
        this.playerRepository = playerRepository;
    }

    public List<Player> GetAllPlayers()
    {
        return playerRepository.findAll();
    }

    public Optional<Player> GetPlayerById(Long Id)
    {
        return playerRepository.findById(Id);
    }

    public Player CreatePlayer(Player player)
    {
        return playerRepository.save(player);
    }

    public void DeletePlayer(Long Id)
    {
        playerRepository.deleteById(Id);
    }

    public Optional<Player> FindByNickName(String nickname)
    {
        return playerRepository.findByNickName(nickname);
    }
}
