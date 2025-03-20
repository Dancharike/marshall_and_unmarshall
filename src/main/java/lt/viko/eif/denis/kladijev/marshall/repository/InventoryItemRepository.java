package lt.viko.eif.denis.kladijev.marshall.repository;

import lt.viko.eif.denis.kladijev.marshall.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long>
{
    List<InventoryItem> findByPlayerId(Long playerId);
    List<InventoryItem> findByGameId(Long gameId);
}