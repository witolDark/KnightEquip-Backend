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
        return service.getAllItems().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/by-type/{type}")
    public List<InventoryItemDTO> getAllItemsByType(@PathVariable ItemType type) {
        List<InventoryItem> items = service.getItemsByType(type);
        return items.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryItemDTO> getItemById(@PathVariable Long id) {
        Optional<InventoryItem> item = service.getItemById(id);
        return item.map(value -> ResponseEntity.ok(convertToDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public InventoryItemDTO addItem() {
        return convertToDTO(service.addItem());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        service.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    private InventoryItemDTO convertToDTO(InventoryItem item) {
        InventoryItemDTO itemDTO = new InventoryItemDTO();
        itemDTO.setLevel(item.getLevel());
        itemDTO.setRarity(item.getRarity());
        itemDTO.setType(item.getType());
        itemDTO.setMainStat(item.getMainStat());
        itemDTO.setMainStatValue(item.getMainStatValue());
        itemDTO.setWeight(item.getWeight());
        return itemDTO;
    }

    private InventoryItem convertToEntity(InventoryItemDTO itemDTO) {
        InventoryItem item = new InventoryItem();
        item.setLevel(itemDTO.getLevel());
        item.setRarity(itemDTO.getRarity());
        item.setType(itemDTO.getType());
        item.setMainStat(itemDTO.getMainStat());
        item.setMainStatValue(itemDTO.getMainStatValue());
        item.setWeight(itemDTO.getWeight());
        return item;
    }
}
