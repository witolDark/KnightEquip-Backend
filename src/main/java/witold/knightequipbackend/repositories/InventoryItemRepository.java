package witold.knightequipbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import witold.knightequipbackend.entities.InventoryItem;
import witold.knightequipbackend.enums.ItemType;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findByType(ItemType type);
}

