package lt.viko.eif.denis.kladijev.marshall.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link InventoryItem} class.
 */
public class InventoryItemTest
{

    /**
     * Tests the getters and setters of the InventoryItem class.
     */
    @Test
    public void testInventoryItemGettersAndSetters()
    {
        Player player = new Player("JaneDoe", "jane.doe@example.com", 5, 300);
        Game game = new Game("Adventure Game", "Adventure", "Exciting game", player, null, null);

        InventoryItem item = new InventoryItem();
        item.setId(1L);
        item.setItemName("Test Item");
        item.setItemDescription("Test Description");
        item.setCost(150.0f);
        item.setPlayer(player);
        item.setGame(game);

        assertEquals(1L, item.getId());
        assertEquals("Test Item", item.getItemName());
        assertEquals("Test Description", item.getItemDescription());
        assertEquals(150.0f, item.getCost());
        assertEquals(player, item.getPlayer());
        assertEquals(game, item.getGame());
    }
}
