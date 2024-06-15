package witold.knightequipbackend.controllersTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import witold.knightequipbackend.controllers.InventoryItemController;
import witold.knightequipbackend.entities.InventoryItem;
import witold.knightequipbackend.services.InventoryItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(InventoryItemController.class)
@AutoConfigureMockMvc
public class InventoryItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private InventoryItemService service;

    @InjectMocks
    private InventoryItemController controller;

    @Test
    void testGetAllItems() throws Exception {
        List<InventoryItem> items = new ArrayList<>();
        items.add(new InventoryItem());
        items.add(new InventoryItem());

        Page<InventoryItem> page = new PageImpl<>(items);

        when(service.getAllItems(0, 10, new String[]{"level", "DESC"}, null))
                .thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/inventory")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "level,DESC")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(items.size()));
    }

    @Test
    void testGetItemById() throws Exception {
        Long itemId = 1L;
        InventoryItem item = new InventoryItem();

        when(service.getItemById(itemId))
                .thenReturn(Optional.of(item));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/inventory/{id}", itemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(item.getId()));
    }

    @Test
    void testAddItem() throws Exception {
        InventoryItem newItem = new InventoryItem();

        when(service.addItem())
                .thenReturn(newItem);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/inventory")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(newItem.getId()));
    }
}

