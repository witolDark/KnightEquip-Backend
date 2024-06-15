package witold.knightequipbackend.servicesTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import witold.knightequipbackend.entities.InventoryItem;
import witold.knightequipbackend.entities.Knight;
import witold.knightequipbackend.repositories.InventoryItemRepository;
import witold.knightequipbackend.repositories.KnightRepository;
import witold.knightequipbackend.services.KnightService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KnightServiceTest {

    @Mock
    private KnightRepository knightRepository;

    @Mock
    private InventoryItemRepository inventoryItemRepository;

    @InjectMocks
    private KnightService knightService;

    @BeforeEach
    public void init() {
        // Initialize mock repositories
    }

    @Test
    public void testGetKnight() {
        Knight knight = new Knight();
        when(knightRepository.findFirstBy()).thenReturn(Optional.of(knight));

        Optional<Knight> result = knightService.getKnight();

        assertNotNull(result);
        assertEquals(knight, result.get());
    }

    @Test
    public void testGetKnightById() {
        Knight knight = new Knight();
        when(knightRepository.findById(any(Integer.class))).thenReturn(Optional.of(knight));

        Knight result = knightService.getKnightById(1);

        assertNotNull(result);
        assertEquals(knight, result);
    }

    @Test
    public void testAddKnight() {
        Knight knight = new Knight();
        when(knightRepository.save(any(Knight.class))).thenReturn(knight);

        Knight result = knightService.addKnight();

        assertNotNull(result);
        assertEquals(knight, result);
    }

    @Test
    public void testUpdateKnightByInventoryItem() {
        Knight knight = new Knight();
        InventoryItem item = new InventoryItem();
        when(knightRepository.findFirstBy()).thenReturn(Optional.of(knight));
        when(inventoryItemRepository.findById(any(Long.class))).thenReturn(Optional.of(item));

        Knight result = knightService.updateKnightByInventoryItem(1L);

        assertNull(result);
        assertNotEquals(knight, result);
    }

    @Test
    public void testUnequipByInventoryItem() {
        Knight knight = new Knight();
        InventoryItem item = new InventoryItem();
        when(knightRepository.findFirstBy()).thenReturn(Optional.of(knight));
        when(inventoryItemRepository.findById(any(Long.class))).thenReturn(Optional.of(item));

        Knight result = knightService.unequipByInventoryItem(1L);

        assertNull(result);
        assertNotEquals(knight, result);
    }

    @Test
    public void testGetKnightByIdNotFound() {
        when(knightRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        assertNull(knightService.getKnightById(1));
    }

    @Test
    public void testUpdateKnightByInventoryItemNotFound() {
        when(knightRepository.findFirstBy()).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> knightService.updateKnightByInventoryItem(1L));
        assertEquals("Knight not found", exception.getMessage());
    }

    @Test
    public void testUnequipByInventoryItemNotFound() {
        when(knightRepository.findFirstBy()).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> knightService.unequipByInventoryItem(1L));
        assertEquals("Knight not found", exception.getMessage());
    }
}