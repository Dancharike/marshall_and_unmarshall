package lt.viko.eif.denis.kladijev.marshall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.viko.eif.denis.kladijev.marshall.model.InventoryItem;
import lt.viko.eif.denis.kladijev.marshall.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for {@link InventoryItemController}.
 */
@WebMvcTest(InventoryItemController.class)
public class InventoryItemControllerTest
{
    @Autowired private MockMvc mockMvc;
    @MockBean private InventoryService inventoryService;
    @Autowired private ObjectMapper objectMapper;

    /**
     * Tests retrieval of all inventory items.
     */
    @Test
    public void testGetAllInventoryItems() throws Exception
    {
        InventoryItem item1 = new InventoryItem("Sword", "Sharp sword", 150.0f, null, null);
        item1.setId(1L);
        InventoryItem item2 = new InventoryItem("Shield", "Sturdy shield", 200.0f, null, null);
        item2.setId(2L);
        Mockito.when(inventoryService.getAll()).thenReturn(Arrays.asList(item1, item2));

        mockMvc.perform(get("/api/inventory")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    /**
     * Tests retrieval of an inventory item by a valid ID.
     */
    @Test
    public void testGetInventoryItemById_Found() throws Exception
    {
        InventoryItem item = new InventoryItem("Sword", "Sharp sword", 150.0f, null, null);
        item.setId(1L);
        Mockito.when(inventoryService.getById(1L)).thenReturn(Optional.of(item));

        mockMvc.perform(get("/api/inventory/1")).andExpect(status().isOk()).andExpect(jsonPath("$.itemName", is("Sword")));
    }

    /**
     * Tests creation of a new inventory item.
     */
    @Test
    public void testCreateInventoryItem() throws Exception
    {
        InventoryItem item = new InventoryItem("Sword", "Sharp sword", 150.0f, null, null);
        item.setId(1L);
        Mockito.when(inventoryService.save(Mockito.any(InventoryItem.class))).thenReturn(item);

        String jsonContent = objectMapper.writeValueAsString(item);
        mockMvc.perform(post("/api/inventory").contentType(MediaType.APPLICATION_JSON).content(jsonContent)).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.itemName", is("Sword")));
    }

    /**
     * Tests deletion of an inventory item by its ID.
     */
    @Test
    public void testDeleteInventoryItem() throws Exception
    {
        Mockito.doNothing().when(inventoryService).delete(1L);
        mockMvc.perform(delete("/api/inventory/1")).andExpect(status().isNoContent());
    }

    /**
     * Tests retrieval of inventory items by player ID.
     */
    @Test
    public void testGetItemByPlayerId() throws Exception
    {
        InventoryItem item = new InventoryItem("Sword", "Sharp sword", 150.0f, null, null);
        item.setId(1L);
        Mockito.when(inventoryService.getByPlayerId(1L)).thenReturn(Arrays.asList(item));

        mockMvc.perform(get("/api/inventory/player/1")).andExpect(status().isOk()).andExpect(jsonPath("$[0].itemName", is("Sword")));
    }

    /**
     * Tests retrieval of inventory items by game ID.
     */
    @Test
    public void testGetItemByGameId() throws Exception
    {
        InventoryItem item = new InventoryItem("Shield", "Sturdy shield", 200.0f, null, null);
        item.setId(1L);
        Mockito.when(inventoryService.getByGameId(1L)).thenReturn(Arrays.asList(item));

        mockMvc.perform(get("/api/inventory/game/1")).andExpect(status().isOk()).andExpect(jsonPath("$[0].itemDescription", is("Sturdy shield")));
    }
}
