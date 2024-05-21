package witold.knightequipbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import witold.knightequipbackend.dtos.InventoryItemDTO;
import witold.knightequipbackend.entities.InventoryItem;
import witold.knightequipbackend.enums.ItemType;
import witold.knightequipbackend.services.InventoryItemService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryItemController {

    private final InventoryItemService service;

    @Autowired
    public InventoryItemController(InventoryItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<InventoryItemDTO> getAllItems() {
        return service.getAllItems().stream().map(InventoryItemDTO::fromEntity).collect(Collectors.toList());
    }

    @GetMapping("/by-type/{type}")
    public List<InventoryItemDTO> getAllItemsByType(@PathVariable ItemType type) {
        List<InventoryItem> items = service.getItemsByType(type);
        return items.stream().map(InventoryItemDTO::fromEntity).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryItemDTO> getItemById(@PathVariable Long id) {
        Optional<InventoryItem> item = service.getItemById(id);
        return item.map(value -> ResponseEntity.ok(InventoryItemDTO.fromEntity(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public InventoryItemDTO addItem() {
        return InventoryItemDTO.fromEntity(service.addItem());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        service.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
