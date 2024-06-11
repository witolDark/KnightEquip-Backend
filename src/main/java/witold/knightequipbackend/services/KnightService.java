package witold.knightequipbackend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import witold.knightequipbackend.entities.InventoryItem;
import witold.knightequipbackend.entities.Knight;
import witold.knightequipbackend.repositories.InventoryItemRepository;
import witold.knightequipbackend.repositories.KnightRepository;

import java.util.Optional;

@Service
public class KnightService {

    private static final Logger logger = LoggerFactory.getLogger(KnightService.class);

    private final KnightRepository repository;
    private final InventoryItemRepository inventoryItemRepository;

    @Autowired
    public KnightService(KnightRepository repository, InventoryItemRepository inventoryItemRepository) {
        this.repository = repository;
        this.inventoryItemRepository = inventoryItemRepository;
    }

    public Optional<Knight> getKnight() {
        logger.info("Fetching the first knight from the repository");
        return repository.findFirstBy();
    }

    public Knight getKnightById(Integer id) {
        logger.info("Fetching knight with ID: {}", id);
        Optional<Knight> knight = repository.findById(id);
        if (knight.isPresent()) {
            logger.info("Knight found with ID: {}", id);
        } else {
            logger.warn("Knight not found with ID: {}", id);
        }
        return knight.orElse(null);
    }

    public Knight addKnight() {
        logger.info("Adding a new knight to the repository");
        Knight knight = new Knight();
        Knight savedKnight = repository.save(knight);
        logger.info("Knight added with ID: {}", savedKnight.getId());
        return savedKnight;
    }

    public Knight updateKnightByInventoryItem(Long id) {
        logger.info("Updating knight with new inventory item with ID: {}", id);
        Knight knight = repository.findFirstBy()
                .orElseThrow(() -> {
                    logger.error("Knight not found");
                    return new RuntimeException("Knight not found");
                });
        InventoryItem itemToSwitch = inventoryItemRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("InventoryItem not found with ID: {}", id);
                    return new RuntimeException("InventoryItem not found");
                });

        knight.equipItem(itemToSwitch);
        Knight updatedKnight = repository.save(knight);
        logger.info("Knight updated with new inventory item. Knight ID: {}, Inventory Item ID: {}", knight.getId(), id);
        return updatedKnight;
    }

    public Knight unequipByInventoryItem(Long id) {
        logger.info("Unequipping inventory item with ID: {} from knight", id);
        Knight knight = repository.findFirstBy()
                .orElseThrow(() -> {
                    logger.error("Knight not found");
                    return new RuntimeException("Knight not found");
                });
        InventoryItem itemToUnequip = inventoryItemRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("InventoryItem not found with ID: {}", id);
                    return new RuntimeException("InventoryItem not found");
                });

        knight.unequipItem(itemToUnequip);
        Knight updatedKnight = repository.save(knight);
        logger.info("Knight unequipped with inventory item. Knight ID: {}, Inventory Item ID: {}", knight.getId(), id);
        return updatedKnight;
    }

    public void deleteKnight(Integer id) {
        logger.info("Deleting knight with ID: {}", id);
        repository.deleteById(id);
        logger.info("Knight deleted with ID: {}", id);
    }
}
