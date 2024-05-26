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
            return Rarity.LEGENDARY;
        else if (this.level > 12)
            return Rarity.ELEGANT;
        else
            return Rarity.COMMON;
    }

    private Double getMainStatValueByLevel() {
        return switch (mainStat) {
            case CRIT_DMG -> (double) Math.round(level * 1.655 * 2);
            case CRIT_RATE -> (double) Math.round(level * 1.655);
            case ATTACK_PERCENT -> (double) Math.round(level * 2.43);
            case ELEMENTAL_MASTERY -> (double) Math.round(level * 8.4);
            case ENERGY_RECHARGE -> (double) Math.round(level * 2.28);
        };
    }

    private Double getWeightByLevelAndType() {
        return switch (type) {
            case HELMET, BOOTS -> Math.round(this.level * 0.2 * 10.0) / 10.0;
            case CHESTPLATE, SWORD -> Math.round(this.level * 0.4 * 10.0) / 10.0;
            case CLOAK -> Math.round(this.level * 0.1 * 10.0) / 10.0;
        };
    }

//    private Map<SubStats, Double> generateRandomSubStats() {
//        Map<SubStats, Double> subStatsMap = new EnumMap<>(SubStats.class);
//        List<SubStats> allSubStats = Stream.of(SubStats.values()).collect(Collectors.toList());
//        Collections.shuffle(allSubStats);
//        for (int i = 0; i < 4; i++) {
//            subStatsMap.put(allSubStats.get(i), (Math.random() * 10) + 1);
//        }
//        return subStatsMap;
//    }
//
//    private void adjustSubStats() {
//        int levelDiv = this.level / 4;
//        for (int i = 0; i < levelDiv; i++) {
//            List<SubStats> keys = new ArrayList<>(this.subStats.keySet());
//            int randomIndex = (int) (Math.random() * keys.size());
//            SubStat randomSubStat = keys.get(randomIndex);
//            this.subStats.put(randomSubStat, this.subStats.get(randomSubStat) + 7.6);
//        }
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
