package controller;

import model.InventoryItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.InventoryService;

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
    public ResponseEntity<List<InventoryItem>> getInventoryByPlayerId(@PathVariable Long playerId)
    {
        return ResponseEntity.ok(inventoryService.GetInventoryByPlayerId(playerId));
    }

    @PostMapping
    public ResponseEntity<InventoryItem> addItemToInventory(@RequestBody InventoryItem item)
    {
        return ResponseEntity.ok(inventoryService.AddItemToInventory(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id)
    {
        inventoryService.DeleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
