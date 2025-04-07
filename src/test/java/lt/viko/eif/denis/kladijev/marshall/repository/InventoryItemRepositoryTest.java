package lt.viko.eif.denis.kladijev.marshall.repository;

import lt.viko.eif.denis.kladijev.marshall.model.Game;
import lt.viko.eif.denis.kladijev.marshall.model.InventoryItem;
import lt.viko.eif.denis.kladijev.marshall.model.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link InventoryItemRepository}.
 */
@DataJpaTest
public class InventoryItemRepositoryTest
{
    @Autowired private InventoryItemRepository inventoryItemRepository;
    @Autowired private PlayerRepository playerRepository;
    @Autowired private GameRepository gameRepository;

    /**
     * Tests that an InventoryItem can be saved and then found by the Player's ID.
     */
    @Test
    public void testSaveAndFindInventoryItemByPlayerId()
    {
        Player player = new Player("InventoryPlayer", "invplayer@example.com", 6, 600);
        player = playerRepository.save(player);

        Game game = new Game("Inventory Game", "Strategy", "Strategy game", player, null, null);
        game = gameRepository.save(game);

        InventoryItem item = new InventoryItem("Sword", "Sharp sword", 150.0f, player, game);
        item = inventoryItemRepository.save(item);

        List<InventoryItem> items = inventoryItemRepository.findByPlayerId(player.getId());
        assertThat(items).isNotEmpty();
        assertThat(items.get(0).getItemName()).isEqualTo("Sword");
    }

    /**
     * Tests that an InventoryItem can be found by the Game's ID.
     */
    @Test
    public void testFindInventoryItemByGameId()
    {
        Player player = new Player("GameInventory", "gameinv@example.com", 7, 700);
        player = playerRepository.save(player);

        Game game = new Game("Game Inventory", "Action", "Action game", player, null, null);
        game = gameRepository.save(game);

        InventoryItem item = new InventoryItem("Shield", "Sturdy shield", 200.0f, player, game);
        item = inventoryItemRepository.save(item);

        List<InventoryItem> items = inventoryItemRepository.findByGameId(game.getId());
        assertThat(items).isNotEmpty();
        assertThat(items.get(0).getItemDescription()).contains("Sturdy shield");
    }
}
