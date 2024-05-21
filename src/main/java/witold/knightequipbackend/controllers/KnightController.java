package witold.knightequipbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import witold.knightequipbackend.entities.Knight;
import witold.knightequipbackend.services.KnightService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/knights")
public class KnightController {

    private final KnightService service;

    @Autowired
    public KnightController(KnightService service) {
        this.service = service;
    }

    @GetMapping
    public List<Knight> getAllKnights() {
        return service.getAllKnights();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Knight> getKnightById(@PathVariable Integer id) {
        Knight knight = service.getKnightById(id);
        if (knight != null) {
            return ResponseEntity.ok(knight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public Knight addKnight() {
        return service.addKnight();
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Knight> updateKnightByInventoryItem(@PathVariable Long itemId) {
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
