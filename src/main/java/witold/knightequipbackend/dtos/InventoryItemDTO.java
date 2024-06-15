package witold.knightequipbackend.dtos;

import witold.knightequipbackend.entities.InventoryItem;
import witold.knightequipbackend.enums.ItemType;
import witold.knightequipbackend.enums.MainStats;
import witold.knightequipbackend.enums.Rarity;

public class InventoryItemDTO {

    private Long id;
    private Integer level;
    private Rarity rarity;
    private ItemType type;
    private MainStats mainStat;
    private Double mainStatValue;
    private Double weight;

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

    public static InventoryItemDTO fromEntity(InventoryItem item) {
        InventoryItemDTO itemDTO = new InventoryItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setLevel(item.getLevel());
        itemDTO.setRarity(item.getRarity());
        itemDTO.setType(item.getType());
        itemDTO.setMainStat(item.getMainStat());
        itemDTO.setMainStatValue(item.getMainStatValue());
        itemDTO.setWeight(item.getWeight());
        return itemDTO;
    }

    public static InventoryItem toEntity(InventoryItemDTO itemDTO) {
        if (itemDTO == null)
            return null;
        InventoryItem item = new InventoryItem();
        item.setId(itemDTO.getId());
        item.setLevel(itemDTO.getLevel());
        item.setRarity(itemDTO.getRarity());
        item.setType(itemDTO.getType());
        item.setMainStat(itemDTO.getMainStat());
        item.setMainStatValue(itemDTO.getMainStatValue());
        item.setWeight(itemDTO.getWeight());
        return item;
    }
}
