package witold.knightequipbackend.dtosTest;

import org.junit.jupiter.api.Test;
import witold.knightequipbackend.dtos.InventoryItemDTO;
import witold.knightequipbackend.dtos.KnightDTO;
import witold.knightequipbackend.entities.Knight;
import witold.knightequipbackend.entities.InventoryItem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class KnightDTOTest {

    @Test
    void testFromEntity() {
        Knight knightEntity = new Knight();
        knightEntity.setId(1);
        knightEntity.setBaseHp(100);
        knightEntity.setBaseDamage(50);
        knightEntity.setAdditionalDamage(20);
        knightEntity.setBaseArmour(80);
        knightEntity.setElementalMastery(30);
        knightEntity.setEnergyRecharge(0.15);
        knightEntity.setCritDamage(2.5);
        knightEntity.setCritRate(0.3);

        InventoryItem helmet = new InventoryItem();
        helmet.setId(1L);
        knightEntity.setHelmet(helmet);

        KnightDTO dto = KnightDTO.fromEntity(knightEntity);

        assertEquals(knightEntity.getId(), dto.getId());
        assertEquals(knightEntity.getBaseHp(), dto.getBaseHp());
        assertEquals(knightEntity.getBaseDamage(), dto.getBaseDamage());
        assertEquals(knightEntity.getAdditionalDamage(), dto.getAdditionalDamage());
        assertEquals(knightEntity.getBaseArmour(), dto.getBaseArmour());
        assertEquals(knightEntity.getElementalMastery(), dto.getElementalMastery());
        assertEquals(knightEntity.getEnergyRecharge(), dto.getEnergyRecharge());
        assertEquals(knightEntity.getCritDamage(), dto.getCritDamage());
        assertEquals(knightEntity.getCritRate(), dto.getCritRate());
        assertEquals(knightEntity.getHelmet().getId(), dto.getHelmet().getId());
    }

    @Test
    void testToEntity() {
        KnightDTO dto = new KnightDTO();
        dto.setId(1);
        dto.setBaseHp(100);
        dto.setBaseDamage(50);
        dto.setAdditionalDamage(20);
        dto.setBaseArmour(80);
        dto.setElementalMastery(30);
        dto.setEnergyRecharge(0.15);
        dto.setCritDamage(2.5);
        dto.setCritRate(0.3);

        InventoryItemDTO helmetDTO = new InventoryItemDTO();
        helmetDTO.setId(1L);
        dto.setHelmet(helmetDTO);

        Knight knightEntity = KnightDTO.toEntity(dto);

        assertEquals(dto.getId(), knightEntity.getId());
        assertEquals(dto.getBaseHp(), knightEntity.getBaseHp());
        assertEquals(dto.getBaseDamage(), knightEntity.getBaseDamage());
        assertEquals(dto.getAdditionalDamage(), knightEntity.getAdditionalDamage());
        assertEquals(dto.getBaseArmour(), knightEntity.getBaseArmour());
        assertEquals(dto.getElementalMastery(), knightEntity.getElementalMastery());
        assertEquals(dto.getEnergyRecharge(), knightEntity.getEnergyRecharge());
        assertEquals(dto.getCritDamage(), knightEntity.getCritDamage());
        assertEquals(dto.getCritRate(), knightEntity.getCritRate());
        assertEquals(dto.getHelmet().getId(), knightEntity.getHelmet().getId());
    }

    @Test
    void testFromEntityNullFields() {
        Knight knightEntity = new Knight();
        knightEntity.setId(1);

        KnightDTO dto = KnightDTO.fromEntity(knightEntity);

        assertEquals(knightEntity.getId(), dto.getId());
        assertNull(dto.getBaseHp());
        assertNull(dto.getBaseDamage());
        assertNull(dto.getAdditionalDamage());
        assertNull(dto.getBaseArmour());
        assertNull(dto.getElementalMastery());
        assertNull(dto.getEnergyRecharge());
        assertNull(dto.getCritDamage());
        assertNull(dto.getCritRate());
        assertNull(dto.getHelmet());
    }

    @Test
    void testToEntityNullFields() {
        KnightDTO dto = new KnightDTO();
        dto.setId(1);

        Knight knightEntity = KnightDTO.toEntity(dto);

        assertEquals(dto.getId(), knightEntity.getId());
        assertEquals(0, knightEntity.getBaseHp());
        assertEquals(0, knightEntity.getBaseDamage());
        assertEquals(0, knightEntity.getAdditionalDamage());
        assertEquals(0, knightEntity.getBaseArmour());
        assertEquals(0, knightEntity.getElementalMastery());
        assertEquals(0.0, knightEntity.getEnergyRecharge());
        assertEquals(0.0, knightEntity.getCritDamage());
        assertEquals(0.0, knightEntity.getCritRate());
        assertNull(knightEntity.getHelmet());
    }
}
