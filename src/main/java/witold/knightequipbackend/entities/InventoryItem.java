package witold.knightequipbackend.entities;

import jakarta.persistence.*;
import witold.knightequipbackend.enums.ItemType;
import witold.knightequipbackend.enums.MainStats;
import witold.knightequipbackend.enums.Rarity;

@Entity
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer level;
    @Enumerated(EnumType.STRING)
    private Rarity rarity;
    @Enumerated(EnumType.STRING)
    private ItemType type;
    @Enumerated(EnumType.STRING)
    private MainStats mainStat;
    private Double mainStatValue;
    private Double weight;

    public InventoryItem() {
        this.level = (int) (Math.random() * 20 + 1);
        this.rarity = getRarityByLevel();
        this.type = ItemType.values()[(int) (Math.random() * ItemType.values().length)];
        this.mainStat = MainStats.values()[(int) (Math.random() * MainStats.values().length)];
        this.mainStatValue = getMainStatValueByLevel();
        this.weight = getWeightByLevelAndType();
    }

    private Rarity getRarityByLevel() {
        if (this.level > 16)
            return Rarity.Legendary;
        else if (this.level > 12)
            return Rarity.Elegant;
        else
            return Rarity.Common;
    }

    private Double getMainStatValueByLevel() {
        return switch (mainStat) {
            case CritDmg -> (double) Math.round(level * 1.655 * 2);
            case CritRate -> (double) Math.round(level * 1.655);
            case Attack -> (double) Math.round(level * 2.43);
            case ElementalMastery -> (double) Math.round(level * 8.4);
            case EnegryRecharge -> (double) Math.round(level * 2.28);
        };
    }

    private Double getWeightByLevelAndType() {
        return switch (type) {
            case Helmet, Boots -> Math.round(this.level * 0.2 * 10.0) / 10.0;
            case Chestplate, Sword -> Math.round(this.level * 0.4 * 10.0) / 10.0;
            case Cloak -> Math.round(this.level * 0.1 * 10.0) / 10.0;
        };
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public MainStats getMainStat() {
        return mainStat;
    }

    public void setMainStat(MainStats mainStat) {
        this.mainStat = mainStat;
    }

    public Double getMainStatValue() {
        return mainStatValue;
    }

    public void setMainStatValue(Double mainStatValue) {
        this.mainStatValue = mainStatValue;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
