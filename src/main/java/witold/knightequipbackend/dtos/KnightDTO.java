package witold.knightequipbackend.dtos;

import witold.knightequipbackend.entities.Knight;

public class KnightDTO {

    private Integer id;
    private Integer baseHp;
    private Integer baseDamage;
    private Integer additionalDamage;
    private Integer baseArmour;
    private Integer elementalMastery;
    private Double energyRecharge;
    private Double critDamage;
    private Double critRate;
    private InventoryItemDTO helmet;
    private InventoryItemDTO cloak;
    private InventoryItemDTO chestplate;
    private InventoryItemDTO boots;
    private InventoryItemDTO sword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBaseHp() {
        return baseHp;
    }

    public void setBaseHp(Integer baseHp) {
        this.baseHp = baseHp;
    }

    public Integer getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(Integer baseDamage) {
        this.baseDamage = baseDamage;
    }

    public Integer getAdditionalDamage() {
        return additionalDamage;
    }

    public void setAdditionalDamage(Integer additionalDamage) {
        this.additionalDamage = additionalDamage;
    }

    public Integer getBaseArmour() {
        return baseArmour;
    }

    public void setBaseArmour(Integer baseArmour) {
        this.baseArmour = baseArmour;
    }

    public Integer getElementalMastery() {
        return elementalMastery;
    }

    public void setElementalMastery(Integer elementalMastery) {
        this.elementalMastery = elementalMastery;
    }

    public Double getEnergyRecharge() {
        return energyRecharge;
    }

    public void setEnergyRecharge(Double energyRecharge) {
        this.energyRecharge = energyRecharge;
    }

    public Double getCritDamage() {
        return critDamage;
    }

    public void setCritDamage(Double critDamage) {
        this.critDamage = critDamage;
    }

    public Double getCritRate() {
        return critRate;
    }

    public void setCritRate(Double critRate) {
        this.critRate = critRate;
    }

    public InventoryItemDTO getHelmet() {
        return helmet;
    }

    public void setHelmet(InventoryItemDTO helmet) {
        this.helmet = helmet;
    }

    public InventoryItemDTO getCloak() {
        return cloak;
    }

    public void setCloak(InventoryItemDTO cloak) {
        this.cloak = cloak;
    }

    public InventoryItemDTO getChestplate() {
        return chestplate;
    }

    public void setChestplate(InventoryItemDTO chestplate) {
        this.chestplate = chestplate;
    }

    public InventoryItemDTO getBoots() {
        return boots;
    }

    public void setBoots(InventoryItemDTO boots) {
        this.boots = boots;
    }

    public InventoryItemDTO getSword() {
        return sword;
    }

    public void setSword(InventoryItemDTO sword) {
        this.sword = sword;
    }

    public static KnightDTO fromEntity(Knight knight) {
        KnightDTO dto = new KnightDTO();
        dto.setId(knight.getId());
        dto.setBaseHp(knight.getBaseHp());
        dto.setBaseDamage(knight.getBaseDamage());
        dto.setAdditionalDamage(knight.getAdditionalDamage());
        dto.setBaseArmour(knight.getBaseArmour());
        dto.setElementalMastery(knight.getElementalMastery());
        dto.setEnergyRecharge(knight.getEnergyRecharge());
        dto.setCritDamage(knight.getCritDamage());
        dto.setCritRate(knight.getCritRate());
        dto.setHelmet(InventoryItemDTO.fromEntity(knight.getHelmet()));
        dto.setCloak(InventoryItemDTO.fromEntity(knight.getCloak()));
        dto.setChestplate(InventoryItemDTO.fromEntity(knight.getChestplate()));
        dto.setBoots(InventoryItemDTO.fromEntity(knight.getBoots()));
        dto.setSword(InventoryItemDTO.fromEntity(knight.getSword()));
        return dto;
    }

    public static Knight toEntity(KnightDTO dto) {
        Knight knight = new Knight();
        knight.setId(dto.getId());
        knight.setBaseHp(dto.getBaseHp());
        knight.setBaseDamage(dto.getBaseDamage());
        knight.setAdditionalDamage(dto.getAdditionalDamage());
        knight.setBaseArmour(dto.getBaseArmour());
        knight.setElementalMastery(dto.getElementalMastery());
        knight.setEnergyRecharge(dto.getEnergyRecharge());
        knight.setCritDamage(dto.getCritDamage());
        knight.setCritRate(dto.getCritRate());
        knight.setHelmet(InventoryItemDTO.toEntity(dto.getHelmet()));
        knight.setCloak(InventoryItemDTO.toEntity(dto.getCloak()));
        knight.setChestplate(InventoryItemDTO.toEntity(dto.getChestplate()));
        knight.setBoots(InventoryItemDTO.toEntity(dto.getBoots()));
        knight.setSword(InventoryItemDTO.toEntity(dto.getSword()));
        return knight;
    }
}
