package witold.knightequipbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public Page<InventoryItem> getAllItems(int page, int size, String[] sort) {
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        return repository.findAll(pageRequest);
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

