package repository;

import model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long>
{
    List<InventoryItem> FindByPlayerId(Long playerId);
}
