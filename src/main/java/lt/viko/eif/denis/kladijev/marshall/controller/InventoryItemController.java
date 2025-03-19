package lt.viko.eif.denis.kladijev.marshall.controller;

import lt.viko.eif.denis.kladijev.marshall.model.InventoryItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lt.viko.eif.denis.kladijev.marshall.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryItemController
{
    private final InventoryService inventoryService;

    public InventoryItemController(InventoryService inventoryService)
    {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<InventoryItem>> GetInventoryByPlayerId(@PathVariable Long playerId)
    {
        return ResponseEntity.ok(inventoryService.GetInventoryByPlayerId(playerId));
    }

    @PostMapping("/player/{playerId}")
    public ResponseEntity<InventoryItem> AddItemToInventory(@PathVariable Long playerId, @RequestBody InventoryItem item)
    {
        InventoryItem createdItem = inventoryService.AddItemToInventory(playerId, item);
        return ResponseEntity.status(201).body(createdItem);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> DeleteItem(@PathVariable Long itemId)
    {
        inventoryService.DeleteItem(itemId);
        return ResponseEntity.noContent().build();
    }
}
