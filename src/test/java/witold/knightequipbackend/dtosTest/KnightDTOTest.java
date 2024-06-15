package witold.knightequipbackend.dtosTest;

import org.junit.jupiter.api.Test;
import witold.knightequipbackend.dtos.KnightDTO;
import witold.knightequipbackend.entities.Knight;

import static org.junit.jupiter.api.Assertions.*;

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
    }

    @Test
    void testToEntity() {
        KnightDTO dto = KnightDTO.fromEntity(new Knight());
        dto.setId(1);
        dto.setBaseHp(100);
        dto.setBaseDamage(50);
        dto.setAdditionalDamage(20);
        dto.setBaseArmour(80);
        dto.setElementalMastery(30);
        dto.setEnergyRecharge(0.15);
        dto.setCritDamage(2.5);
        dto.setCritRate(0.3);

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
    }

    @Test
    void testFromEntityNullFields() {
        Knight knightEntity = new Knight();
        knightEntity.setId(1);

        KnightDTO dto = KnightDTO.fromEntity(knightEntity);

        assertEquals(knightEntity.getId(), dto.getId());
        assertNotNull(dto.getBaseHp());
        assertNotNull(dto.getBaseDamage());
        assertNotNull(dto.getAdditionalDamage());
        assertNotNull(dto.getBaseArmour());
        assertNotNull(dto.getElementalMastery());
        assertNotNull(dto.getEnergyRecharge());
        assertNotNull(dto.getCritDamage());
        assertNotNull(dto.getCritRate());
        assertNull(dto.getHelmet());
    }

    @Test
    void testToEntityNullFields() {
        KnightDTO dto = KnightDTO.fromEntity(new Knight());
        dto.setId(1);

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
    }
}
