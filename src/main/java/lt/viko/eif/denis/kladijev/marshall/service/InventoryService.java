package lt.viko.eif.denis.kladijev.marshall.service;

import lt.viko.eif.denis.kladijev.marshall.model.InventoryItem;
import lt.viko.eif.denis.kladijev.marshall.service.abstraction.AbstractCrudService;
import org.springframework.stereotype.Service;
import lt.viko.eif.denis.kladijev.marshall.repository.InventoryItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService extends AbstractCrudService<InventoryItem, Long>
{
    private final InventoryItemRepository repository;

    public InventoryService(InventoryItemRepository inventoryRepository)
    {
        this.repository = inventoryRepository;
    }

    @Override
    public List<InventoryItem> getAll()
    {
        return repository.findAll();
    }

    @Override
    public Optional<InventoryItem> getById(Long id)
    {
        return repository.findById(id);
    }

    @Override
    public InventoryItem save(InventoryItem item)
    {
        return repository.save(item);
    }

    @Override
    public void delete(Long id)
    {
        repository.deleteById(id);
    }

    /**
     * Get InventoryItem by playerId.
     * @param playerId player ID itself.
     * @return returns playerId.
     */
    public List<InventoryItem> getByPlayerId(Long playerId)
    {
        return repository.findByPlayerId(playerId);
    }

    /**
     * Get InventoryItem by gameId.
     * @param gameId game ID itself.
     * @return returns gameId.
     */
    public List<InventoryItem> getByGameId(Long gameId)
    {
        return repository.findByGameId(gameId);
    }
}
