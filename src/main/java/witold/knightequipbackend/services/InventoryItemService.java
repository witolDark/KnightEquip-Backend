package witold.knightequipbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import witold.knightequipbackend.entities.InventoryItem;
import witold.knightequipbackend.enums.ItemType;
import witold.knightequipbackend.repositories.InventoryItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryItemService {

    private final InventoryItemRepository repository;

    @Autowired
    public InventoryItemService(InventoryItemRepository repository) {
        this.repository = repository;
    }

    public List<InventoryItem> getAllItems() {
        return repository.findAll();
    }

    public List<InventoryItem> getItemsByType(ItemType type) {
        return repository.findByType(type);
    }

    public Optional<InventoryItem> getItemById(Long id) {
        return repository.findById(id);
    }


    public InventoryItem addItem() {
        InventoryItem item = new InventoryItem();
        return repository.save(item);
    }

    public void deleteItem(Long id) {
        repository.deleteById(id);
    }

}

