package service;

import model.InventoryItem;
import model.Player;
import org.springframework.stereotype.Service;
import repository.InventoryItemRepository;
import repository.PlayerRepository;

import java.util.List;

@Service
public class InventoryService
{
    private final InventoryItemRepository inventoryItemRepository;
    private final PlayerRepository playerRepository;

    public InventoryService(InventoryItemRepository inventoryItemRepository, PlayerRepository playerRepository)
    {
        this.inventoryItemRepository = inventoryItemRepository;
        this.playerRepository = playerRepository;
    }

    public List<InventoryItem> GetInventoryByPlayerId(Long playerId)
    {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player not found with id: " + playerId));
        return inventoryItemRepository.FindByPlayerId(player.getId());
    }

    public InventoryItem AddItemToInventory(Long playerId ,InventoryItem item)
    {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Player not found with id: " + playerId));
        item.setPlayer(player);
        return inventoryItemRepository.save(item);
    }

    public void DeleteItem(Long id)
    {
        InventoryItem item = inventoryItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
        inventoryItemRepository.delete(item);
    }
}
