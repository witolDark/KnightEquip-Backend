package witold.knightequipbackend.entities;

import jakarta.persistence.*;

@Entity
public class Knight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer baseHp;
    private Integer baseDamage;
    private Integer additionalDamage;
    private Integer baseArmour;
    private Integer elementalMastery;
    private Double energyRecharge;
    private Double critDamage;
    private Double critRate;
    @OneToOne
    private InventoryItem helmet;
    @OneToOne
    private InventoryItem cloak;
    @OneToOne
    private InventoryItem chestplate;
    @OneToOne
    private InventoryItem boots;
    @OneToOne
    private InventoryItem sword;

    public Knight() {
        baseHp = 10850;
        baseDamage = 350;
        baseArmour = 630;
        additionalDamage = 0;
        elementalMastery = 0;
        critRate = 50.0;
        critDamage = 100.0;
        energyRecharge = 100.0;
        helmet = null;
        cloak = null;
        chestplate = null;
        boots = null;
        sword = null;
    }

    public void equipItem(InventoryItem inventoryItem) {
        switch (inventoryItem.getType()) {
            case BOOTS -> setBoots(inventoryItem);
            case CHESTPLATE -> setChestplate(inventoryItem);
            case HELMET -> setHelmet(inventoryItem);
            case CLOAK -> setCloak(inventoryItem);
            case SWORD -> setSword(inventoryItem);
        }
        updateKnight(inventoryItem);
    }

    public void updateKnight(InventoryItem inventoryItem) {
        switch (inventoryItem.getMainStat()) {
            case CRIT_DMG -> setCritDamage(getCritDamage() + inventoryItem.getMainStatValue());
            case CRIT_RATE -> setCritRate(getCritRate() + inventoryItem.getMainStatValue());
            case ATTACK_PERCENT -> setAdditionalDamage((int) (getBaseDamage() * inventoryItem.getMainStatValue()));
            case ELEMENTAL_MASTERY ->
                    setElementalMastery((int) (getElementalMastery() + inventoryItem.getMainStatValue()));
            case ENERGY_RECHARGE -> setEnergyRecharge(getEnergyRecharge() + inventoryItem.getMainStatValue());
        }
    }

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

    public InventoryItem getHelmet() {
        return helmet;
    }

    public void setHelmet(InventoryItem helmet) {
        this.helmet = helmet;
    }

    public InventoryItem getCloak() {
        return cloak;
    }

    public void setCloak(InventoryItem cloak) {
        this.cloak = cloak;
    }

    public InventoryItem getChestplate() {
        return chestplate;
    }

    public void setChestplate(InventoryItem chestplate) {
        this.chestplate = chestplate;
    }

    public InventoryItem getBoots() {
        return boots;
    }

    public void setBoots(InventoryItem boots) {
        this.boots = boots;
    }

    public InventoryItem getSword() {
        return sword;
    }

    public void setSword(InventoryItem sword) {
        this.sword = sword;
    }
}
