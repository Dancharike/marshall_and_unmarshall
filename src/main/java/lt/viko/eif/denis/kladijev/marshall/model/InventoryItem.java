package lt.viko.eif.denis.kladijev.marshall.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;

/**
 * Basic model of a player's inventory items.
 * The class has fields such as: id, itemName, itemDescription, cost.
 * There are also provided links to Player's and Game's classes.
 */

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@XmlRootElement(name = "InventoryItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class InventoryItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @XmlElement private String itemName;
    @XmlElement private String itemDescription;
    @XmlElement private float cost;

    @ManyToOne
    @JoinColumn(name = "player_id")
    @XmlIDREF
    private Player player;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @XmlIDREF
    private Game game;

    public InventoryItem() {}

    public InventoryItem(String itemName, String itemDescription, float cost, Player player, Game game)
    {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.cost = cost;
        this.player = player;
        this.game = game;
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

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", cost=" + cost +
                ", player=" + player +
                ", game=" + game +
                '}';
    }
}
