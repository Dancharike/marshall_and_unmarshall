package lt.viko.eif.denis.kladijev.marshall.service;

import lt.viko.eif.denis.kladijev.marshall.model.InventoryItem;
import lt.viko.eif.denis.kladijev.marshall.repository.InventoryItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link InventoryService}.
 */
public class InventoryItemServiceTest
{
    @Mock private InventoryItemRepository inventoryItemRepository;
    @InjectMocks private InventoryService inventoryService;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test that getAll returns all inventory items.
     */
    @Test
    public void testGetAllInventoryItems()
    {
        InventoryItem item1 = new InventoryItem("Sword", "Sharp sword", 150.0f, null, null);
        InventoryItem item2 = new InventoryItem("Shield", "Sturdy shield", 200.0f, null, null);
        when(inventoryItemRepository.findAll()).thenReturn(Arrays.asList(item1, item2));

        List<InventoryItem> items = inventoryService.getAll();
        assertThat(items).hasSize(2);
    }

    /**
     * Test that getById returns an inventory item when found.
     */
    @Test
    public void testGetById_Found()
    {
        InventoryItem item = new InventoryItem("Sword", "Sharp sword", 150.0f, null, null);
        item.setId(1L);
        when(inventoryItemRepository.findById(1L)).thenReturn(Optional.of(item));

        Optional<InventoryItem> result = inventoryService.getById(1L);
        assertThat(result).isPresent();
        assertThat(result.get().getItemName()).isEqualTo("Sword");
    }

    /**
     * Test that save persists an inventory item.
     */
    @Test
    public void testSaveInventoryItem()
    {
        InventoryItem item = new InventoryItem("Shield", "Sturdy shield", 200.0f, null, null);
        when(inventoryItemRepository.save(any(InventoryItem.class))).thenAnswer(invocation -> {
            InventoryItem saved = invocation.getArgument(0);
            saved.setId(10L);
            return saved;
        });

        InventoryItem savedItem = inventoryService.save(item);
        assertThat(savedItem.getId()).isEqualTo(10L);
    }

    /**
     * Test that getByPlayerId returns inventory items for a given player id.
     */
    @Test
    public void testGetByPlayerId()
    {
        InventoryItem item = new InventoryItem("Sword", "Sharp sword", 150.0f, null, null);
        when(inventoryItemRepository.findByPlayerId(1L)).thenReturn(Arrays.asList(item));

        List<InventoryItem> items = inventoryService.getByPlayerId(1L);
        assertThat(items).isNotEmpty();
    }

    /**
     * Test that getByGameId returns inventory items for a given game id.
     */
    @Test
    public void testGetByGameId()
    {
        InventoryItem item = new InventoryItem("Shield", "Sturdy shield", 200.0f, null, null);
        when(inventoryItemRepository.findByGameId(1L)).thenReturn(Arrays.asList(item));

        List<InventoryItem> items = inventoryService.getByGameId(1L);
        assertThat(items).isNotEmpty();
    }
}
