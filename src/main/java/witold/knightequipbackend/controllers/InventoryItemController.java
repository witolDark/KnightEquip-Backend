package witold.knightequipbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import witold.knightequipbackend.dtos.InventoryItemDTO;
import witold.knightequipbackend.entities.InventoryItem;
import witold.knightequipbackend.enums.ItemType;
import witold.knightequipbackend.services.InventoryItemService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryItemController {

    private final InventoryItemService service;

    @Autowired
    public InventoryItemController(InventoryItemService service) {
        this.service = service;
    }

    @GetMapping
    public Page<InventoryItemDTO> getAllItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "level,DESC") String[] sort,
            @RequestParam(required = false) ItemType type) {
        return service.getAllItems(page, size, sort, type).map(InventoryItemDTO::fromEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryItemDTO> getItemById(@PathVariable Long id) {
        Optional<InventoryItem> item = service.getItemById(id);
        return item.map(value -> ResponseEntity.ok(InventoryItemDTO.fromEntity(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public InventoryItemDTO addItem() {
        return InventoryItemDTO.fromEntity(service.addItem());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        service.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
