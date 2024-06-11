package witold.knightequipbackend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import witold.knightequipbackend.entities.InventoryItem;
import witold.knightequipbackend.enums.ItemType;
import witold.knightequipbackend.repositories.InventoryItemRepository;

import java.util.Optional;

@Service
public class InventoryItemService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryItemService.class);

    private final InventoryItemRepository repository;

    @Autowired
    public InventoryItemService(InventoryItemRepository repository) {
        this.repository = repository;
    }

    public Page<InventoryItem> getAllItems(int page, int size, String[] sort, ItemType type) {
        logger.info("Fetching all items with pagination - Page: {}, Size: {}, Sort: {}, ItemType: {}", page, size, sort, type);
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        Page<InventoryItem> items;
        if (type != null) {
            logger.info("Filtering items by type: {}", type);
            items = repository.findByType(type, pageable);
        } else {
            items = repository.findAll(pageable);
        }
        logger.info("Fetched {} items", items.getTotalElements());
        return items;
    }

    public Optional<InventoryItem> getItemById(Long id) {
        logger.info("Fetching item with ID: {}", id);
        Optional<InventoryItem> item = repository.findById(id);
        if (item.isPresent()) {
            logger.info("Item found with ID: {}", id);
        } else {
            logger.warn("Item not found with ID: {}", id);
        }
        return item;
    }

    public InventoryItem addItem() {
        logger.info("Adding a new inventory item");
        InventoryItem item = new InventoryItem();
        InventoryItem savedItem = repository.save(item);
        logger.info("Inventory item added with ID: {}", savedItem.getId());
        return savedItem;
    }

    public void deleteItem(Long id) {
        logger.info("Deleting inventory item with ID: {}", id);
        repository.deleteById(id);
        logger.info("Inventory item deleted with ID: {}", id);
    }
}

