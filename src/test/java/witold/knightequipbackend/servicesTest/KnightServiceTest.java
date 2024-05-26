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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        // Arrange
        Knight knight = new Knight();
        when(knightRepository.findFirstBy()).thenReturn(Optional.of(knight));

        // Act
        Optional<Knight> result = knightService.getKnight();

        // Assert
        assertNotNull(result);
        assertEquals(knight, result.get());
    }

    @Test
    public void testGetKnightById() {
        // Arrange
        Knight knight = new Knight();
        when(knightRepository.findById(any(Integer.class))).thenReturn(Optional.of(knight));

        // Act
        Knight result = knightService.getKnightById(1);

        // Assert
        assertNotNull(result);
        assertEquals(knight, result);
    }

    @Test
    public void testAddKnight() {
        // Arrange
        Knight knight = new Knight();
        when(knightRepository.save(any(Knight.class))).thenReturn(knight);

        // Act
        Knight result = knightService.addKnight();

        // Assert
        assertNotNull(result);
        assertEquals(knight, result);
    }

    @Test
    public void testUpdateKnightByInventoryItem() {
        // Arrange
        Knight knight = new Knight();
        InventoryItem item = new InventoryItem();
        when(knightRepository.findFirstBy()).thenReturn(Optional.of(knight));
        when(inventoryItemRepository.findById(any(Long.class))).thenReturn(Optional.of(item));

        // Act
        Knight result = knightService.updateKnightByInventoryItem(1L);

        // Assert
        assertNotNull(result);
        assertEquals(knight, result);
    }

    @Test
    public void testUnequipByInventoryItem() {
        // Arrange
        Knight knight = new Knight();
        InventoryItem item = new InventoryItem();
        when(knightRepository.findFirstBy()).thenReturn(Optional.of(knight));
        when(inventoryItemRepository.findById(any(Long.class))).thenReturn(Optional.of(item));

        // Act
        Knight result = knightService.unequipByInventoryItem(1L);

        // Assert
        assertNotNull(result);
        assertEquals(knight, result);
    }

    @Test
    public void testDeleteKnight() {
        // Arrange
        when(knightRepository.findById(any(Integer.class))).thenReturn(Optional.of(new Knight()));

        // Act
        knightService.deleteKnight(1);

        // Assert
        // No assertions needed, just verify that the method was called
    }

    @Test
    public void testGetKnightByIdNotFound() {
        // Arrange
        when(knightRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

        // Act and Assert
        assertEquals(null, knightService.getKnightById(1));
    }

    @Test
    public void testUpdateKnightByInventoryItemNotFound() {
        // Arrange
        when(knightRepository.findFirstBy()).thenReturn(Optional.empty());

        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> knightService.updateKnightByInventoryItem(1L));
        assertEquals("Knight not found", exception.getMessage());
    }

    @Test
    public void testUnequipByInventoryItemNotFound() {
        // Arrange
        when(knightRepository.findFirstBy()).thenReturn(Optional.empty());

        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> knightService.unequipByInventoryItem(1L));
        assertEquals("Knight not found", exception.getMessage());
    }
}