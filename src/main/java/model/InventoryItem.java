package model;

import jakarta.persistence.*;

@Entity
public class InventoryItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private String itemType;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public InventoryItem() {}

    public InventoryItem(String itemName, String itemType, int quantity, Player player)
    {
        this.itemName = itemName;
        this.itemType = itemType;
        this.quantity = quantity;
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemType='" + itemType + '\'' +
                ", quantity=" + quantity +
                ", player=" + player +
                '}';
    }
}
