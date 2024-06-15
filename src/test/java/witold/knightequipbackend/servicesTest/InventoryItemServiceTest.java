package witold.knightequipbackend.servicesTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import witold.knightequipbackend.entities.InventoryItem;
import witold.knightequipbackend.enums.ItemType;
import witold.knightequipbackend.repositories.InventoryItemRepository;
import witold.knightequipbackend.services.InventoryItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class InventoryItemServiceTest {

    @Mock
    private InventoryItemRepository repository;

    @InjectMocks
    private InventoryItemService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllItems() {
        int page = 0;
        int size = 10;
        String[] sort = {"level", "DESC"};
        ItemType type = ItemType.SWORD;
        List<InventoryItem> itemList = new ArrayList<>();
        itemList.add(new InventoryItem());
        Page<InventoryItem> expectedPage = new PageImpl<>(itemList);
        when(repository.findByType(eq(type), any(PageRequest.class))).thenReturn(expectedPage);

        Page<InventoryItem> resultPage = service.getAllItems(page, size, sort, type);

        assertEquals(expectedPage.getTotalElements(), resultPage.getTotalElements());
        verify(repository, times(1)).findByType(eq(type), any(PageRequest.class));
    }

    @Test
    void testGetItemById() {
        Long id = 1L;
        InventoryItem item = new InventoryItem();
        item.setId(id);
        Optional<InventoryItem> optionalItem = Optional.of(item);
        when(repository.findById(id)).thenReturn(optionalItem);

        Optional<InventoryItem> resultItem = service.getItemById(id);

        assertTrue(resultItem.isPresent());
        assertEquals(id, resultItem.get().getId());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void testAddItem() {
        InventoryItem item = new InventoryItem();
        when(repository.save(any(InventoryItem.class))).thenReturn(item);

        InventoryItem resultItem = service.addItem();

        verify(repository, times(1)).save(any(InventoryItem.class));
        assertEquals(item, resultItem);
    }

    @Test
    void testDeleteItem() {
        Long id = 1L;

        service.deleteItem(id);

        verify(repository, times(1)).deleteById(id);
    }
}

