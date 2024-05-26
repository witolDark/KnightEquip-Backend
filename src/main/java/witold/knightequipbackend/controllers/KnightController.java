package witold.knightequipbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import witold.knightequipbackend.dtos.KnightDTO;
import witold.knightequipbackend.entities.Knight;
import witold.knightequipbackend.services.KnightService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/knights")
public class KnightController {

    private final KnightService service;
    private final KnightService knightService;

    @Autowired
    public KnightController(KnightService service, KnightService knightService) {
        this.service = service;
        this.knightService = knightService;
    }

    @GetMapping
    public ResponseEntity<KnightDTO> getKnight() {
        Optional<Knight> knight = knightService.getKnight();
        return knight.map(value -> ResponseEntity.ok(KnightDTO.fromEntity(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Knight addKnight() {
        return service.addKnight();
    }

    @PatchMapping
    public ResponseEntity<Knight> updateKnightByInventoryItem(@RequestParam Long itemId) {
        Knight updatedKnight = service.updateKnightByInventoryItem(itemId);
        if (updatedKnight != null) {
            return ResponseEntity.ok(updatedKnight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKnight(@PathVariable Integer id) {
        service.deleteKnight(id);
        return ResponseEntity.noContent().build();
    }
}
