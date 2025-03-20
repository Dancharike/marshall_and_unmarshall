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
     * Extra method for flexible finding of a player by nickname.
     * @param nickName Player nickname.
     * @return Returns specified nickname.
     */
    public Optional<Player> getByNickName(String nickName)
    {
        return repository.findByNickName(nickName);
    }
}
