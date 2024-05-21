package witold.knightequipbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import witold.knightequipbackend.entities.InventoryItem;
import witold.knightequipbackend.entities.Knight;
import witold.knightequipbackend.repositories.InventoryItemRepository;
import witold.knightequipbackend.repositories.KnightRepository;

import java.util.List;
import java.util.Optional;

@Service
public class KnightService {

    private final KnightRepository repository;
    private final InventoryItemRepository inventoryItemRepository;

    @Autowired
    public KnightService(KnightRepository repository, InventoryItemRepository inventoryItemRepository) {
        this.repository = repository;
        this.inventoryItemRepository = inventoryItemRepository;
    }

    public List<Knight> getAllKnights() {
        return repository.findAll();
    }

    public Knight getKnightById(Integer id) {
        Optional<Knight> knight = repository.findById(id);
        return knight.orElse(null);
    }

    public Knight addKnight() {
        Knight knight = new Knight();
        return repository.save(knight);
    }

    public Knight updateKnightByInventoryItem(Long id) {
        Knight knight = repository.findFirstBy()
                .orElseThrow(() -> new RuntimeException("Knight not found"));
        InventoryItem itemToSwitch = inventoryItemRepository.findById(id).orElseThrow(() -> new RuntimeException("InventoryItem not found"));

        knight.equipItem(itemToSwitch);
        return repository.save(knight);
    }

    public void deleteKnight(Integer id) {
        repository.deleteById(id);
    }
}
