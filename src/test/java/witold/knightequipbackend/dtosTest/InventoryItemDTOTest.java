package witold.knightequipbackend.dtosTest;

import org.junit.jupiter.api.Test;
import witold.knightequipbackend.dtos.InventoryItemDTO;
import witold.knightequipbackend.entities.InventoryItem;
import witold.knightequipbackend.enums.ItemType;
import witold.knightequipbackend.enums.MainStats;
import witold.knightequipbackend.enums.Rarity;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryItemDTOTest {

    @Test
    void testFromEntity() {
        InventoryItem itemEntity = new InventoryItem();
        itemEntity.setId(1L);
        itemEntity.setLevel(5);
        itemEntity.setRarity(Rarity.LEGENDARY);
        itemEntity.setType(ItemType.SWORD);
        itemEntity.setMainStat(MainStats.ATTACK_PERCENT);
        itemEntity.setMainStatValue(12.5);
        itemEntity.setWeight(2.3);

        InventoryItemDTO dto = InventoryItemDTO.fromEntity(itemEntity);

        assertEquals(itemEntity.getId(), dto.getId());
        assertEquals(itemEntity.getLevel(), dto.getLevel());
        assertEquals(itemEntity.getRarity(), dto.getRarity());
        assertEquals(itemEntity.getType(), dto.getType());
        assertEquals(itemEntity.getMainStat(), dto.getMainStat());
        assertEquals(itemEntity.getMainStatValue(), dto.getMainStatValue());
        assertEquals(itemEntity.getWeight(), dto.getWeight());
    }

    @Test
    void testToEntity() {
        InventoryItemDTO dto = new InventoryItemDTO();
        dto.setId(2L);
        dto.setLevel(10);
        dto.setRarity(Rarity.COMMON);
        dto.setType(ItemType.HELMET);
        dto.setMainStat(MainStats.CRIT_DMG);
        dto.setMainStatValue(8.7);
        dto.setWeight(1.8);

        InventoryItem itemEntity = InventoryItemDTO.toEntity(dto);

        assertEquals(dto.getId(), itemEntity.getId());
        assertEquals(dto.getLevel(), itemEntity.getLevel());
        assertEquals(dto.getRarity(), itemEntity.getRarity());
        assertEquals(dto.getType(), itemEntity.getType());
        assertEquals(dto.getMainStat(), itemEntity.getMainStat());
        assertEquals(dto.getMainStatValue(), itemEntity.getMainStatValue());
        assertEquals(dto.getWeight(), itemEntity.getWeight());
    }

    @Test
    void testFromEntityNullFields() {
        InventoryItem itemEntity = new InventoryItem();
        itemEntity.setId(3L);

        InventoryItemDTO dto = InventoryItemDTO.fromEntity(itemEntity);

        assertEquals(itemEntity.getId(), dto.getId());
        assertNotNull(dto.getLevel());
        assertNotNull(dto.getRarity());
        assertNotNull(dto.getType());
        assertNotNull(dto.getMainStat());
        assertNotNull(dto.getMainStatValue());
        assertNotNull(dto.getWeight());
    }

    @Test
    void testToEntityNullFields() {
        InventoryItemDTO dto = new InventoryItemDTO();
        dto.setId(4L);
        dto.setLevel(0);
        dto.setMainStatValue(0.0);
        dto.setWeight(0.0);

        InventoryItem itemEntity = InventoryItemDTO.toEntity(dto);

        assertEquals(dto.getId(), itemEntity.getId());
        assertEquals(0, itemEntity.getLevel());
        assertNull(itemEntity.getRarity());
        assertNull(itemEntity.getType());
        assertNull(itemEntity.getMainStat());
        assertEquals(0.0, itemEntity.getMainStatValue());
        assertEquals(0.0, itemEntity.getWeight());
    }
}

