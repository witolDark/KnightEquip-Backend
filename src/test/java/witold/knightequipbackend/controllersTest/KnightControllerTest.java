package witold.knightequipbackend.controllersTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import witold.knightequipbackend.controllers.KnightController;
import witold.knightequipbackend.dtos.KnightDTO;
import witold.knightequipbackend.entities.Knight;
import witold.knightequipbackend.services.KnightService;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(KnightController.class)
public class KnightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KnightService service;

    @MockBean
    private KnightService knightService;

    @Test
    public void testGetKnight_KnightExists() throws Exception {
        Knight knight = new Knight();
        knight.setId(1);
        KnightDTO knightDTO = KnightDTO.fromEntity(knight);

        when(knightService.getKnight()).thenReturn(Optional.of(knight));

        mockMvc.perform(get("/api/v1/knights"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(knightDTO.getId()));
    }

    @Test
    public void testGetKnight_KnightNotFound() throws Exception {
        when(knightService.getKnight()).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/knights"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddKnight() throws Exception {
        Knight knight = new Knight();
        knight.setId(1);

        when(service.addKnight()).thenReturn(knight);

        mockMvc.perform(post("/api/v1/knights")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(knight.getId()));
    }

    @Test
    public void testUpdateKnightByInventoryItem_ItemExists() throws Exception {
        Knight knight = new Knight();
        knight.setId(1);

        when(service.updateKnightByInventoryItem(1L)).thenReturn(knight);

        mockMvc.perform(patch("/api/v1/knights")
                        .param("itemId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(knight.getId()));
    }

    @Test
    public void testUpdateKnightByInventoryItem_ItemNotFound() throws Exception {
        when(service.updateKnightByInventoryItem(1L)).thenReturn(null);

        mockMvc.perform(patch("/api/v1/knights")
                        .param("itemId", "1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUnequipKnight_ItemExists() throws Exception {
        Knight knight = new Knight();
        knight.setId(1);

        when(service.unequipByInventoryItem(1L)).thenReturn(knight);

        mockMvc.perform(patch("/api/v1/knights/unequip")
                        .param("itemId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(knight.getId()));
    }

    @Test
    public void testUnequipKnight_ItemNotFound() throws Exception {
        when(service.unequipByInventoryItem(1L)).thenReturn(null);

        mockMvc.perform(patch("/api/v1/knights/unequip")
                        .param("itemId", "1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteKnight() throws Exception {
        mockMvc.perform(delete("/api/v1/knights/1"))
                .andExpect(status().isNoContent());
    }
}
