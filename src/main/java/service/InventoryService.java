package service;

import model.InventoryItem;
import org.springframework.stereotype.Service;
import repository.InventoryItemRepository;

import java.util.List;

@Service
public class InventoryService
{
    private final InventoryItemRepository inventoryItemRepository;

    public InventoryService(InventoryItemRepository inventoryItemRepository)
    {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    public List<InventoryItem> GetInventoryByPlayerId(Long playerId)
    {
        return inventoryItemRepository.FindByPlayerId(playerId);
    }

    public InventoryItem AddItemToInventory(InventoryItem item)
    {
        return inventoryItemRepository.save(item);
    }

    public void DeleteItem(Long id)
    {
        inventoryItemRepository.deleteById(id);
    }
}
