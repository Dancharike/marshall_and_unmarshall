package lt.viko.eif.denis.kladijev.marshall.service;

import lt.viko.eif.denis.kladijev.marshall.model.Player;
import lt.viko.eif.denis.kladijev.marshall.service.abstraction.AbstractCrudService;
import org.springframework.stereotype.Service;
import lt.viko.eif.denis.kladijev.marshall.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService extends AbstractCrudService<Player, Long>
{
    private final PlayerRepository repository;

    public PlayerService(PlayerRepository playerRepository)
    {
        this.repository = playerRepository;
    }

    @Override
    public List<Player> getAll()
    {
        return repository.findAll();
    }

    @Override
    public Optional<Player> getById(Long id)
    {
        return repository.findById(id);
    }

    @Override
    public Player save(Player player)
    {
        return repository.save(player);
    }

    @Override
    public void delete(Long id)
    {
        repository.deleteById(id);
    }

    /**
     * Extra method for updating player's fields and collections.
     * @param id player ID.
     * @param updatedPlayer data of updated player.
     * @return saves data in fields of a chosen existing player.
     */
    public Player updatePlayer(Long id, Player updatedPlayer)
    {
        Player existingPlayer = repository.findById(id).orElseThrow(() -> new RuntimeException("Player not found with id " + id));

        existingPlayer.setNickName(updatedPlayer.getNickName());
        existingPlayer.setEmail(updatedPlayer.getEmail());
        existingPlayer.setLevel(updatedPlayer.getLevel());
        existingPlayer.setExperience(updatedPlayer.getExperience());
        existingPlayer.setGames(updatedPlayer.getGames());
        existingPlayer.setAchievements(updatedPlayer.getAchievements());
        existingPlayer.setInventory(updatedPlayer.getInventory());

        return repository.save(existingPlayer);
    }

    /**
     * Extra method for partial fields update of a player.
     * @param id player ID.
     * @param updatedPlayer data of updated player.
     * @return saves data in fields of a chosen existing player.
     */
    public Player patchPlayer(Long id, Player updatedPlayer)
    {
        Player existingPlayer = repository.findById(id).orElseThrow(() -> new RuntimeException("Player not found with id " + id));

        if(updatedPlayer.getNickName() != null)
        {
            existingPlayer.setNickName(updatedPlayer.getNickName());
        }

        if(updatedPlayer.getEmail() != null)
        {
            existingPlayer.setEmail(updatedPlayer.getEmail());
        }

        if(updatedPlayer.getLevel() != 0)
        {
            existingPlayer.setLevel(updatedPlayer.getLevel());
        }

        if(updatedPlayer.getExperience() != 0)
        {
            existingPlayer.setExperience(updatedPlayer.getExperience());
        }

        return repository.save(existingPlayer);
    }

    /**
     * Extra method for flexible finding of a player by nickname.
     * @param nickName Player nickname.
     * @return Returns specified nickname.
     */
    public Optional<Player> getByNickName(String nickName)
    {
        return repository.findByNickName(nickName);
    }
}
