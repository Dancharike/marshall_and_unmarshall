package lt.viko.eif.denis.kladijev.marshall.controller;

import lt.viko.eif.denis.kladijev.marshall.model.InventoryItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lt.viko.eif.denis.kladijev.marshall.service.InventoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class InventoryItemController
{
    private final InventoryService service;

    public InventoryItemController(InventoryService service)
    {
        this.service = service;
    }

    @GetMapping
    public List<InventoryItem> getAllInventoryItems()
    {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryItem> getInventoryItemById(@PathVariable Long id)
    {
        Optional<InventoryItem> item = service.getById(id);
        return item.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public InventoryItem createInventoryItem(@RequestBody InventoryItem inventoryItem)
    {
        return service.save(inventoryItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long id)
    {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
