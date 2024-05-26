package witold.knightequipbackend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import witold.knightequipbackend.entities.InventoryItem;
import witold.knightequipbackend.enums.ItemType;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    Page<InventoryItem> findByType(ItemType type, Pageable pageable);
}

