package lt.viko.eif.denis.kladijev.marshall.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

/**
 * Basic model class for player's games.
 * Class has fields: id, gameTitle, gameGenre, gameDescription.
 * Also provided link to Player's, Achievement's and InventoryItem's classes.
 */

@Entity
@XmlRootElement(name = "Game")
@XmlAccessorType(XmlAccessType.FIELD)
public class Game
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @XmlElement private String gameTitle;
    @XmlElement private String gameGenre;
    @XmlElement private String gameDescription;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @XmlElement(name = "Achievement")
    private List<Achievement> achievements;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @XmlElement(name = "InventoryItem")
    private List<InventoryItem> inventoryItems;

    public Game() {}
    public Game(String title, String genre, String description, Player player, List<Achievement> achievements, List<InventoryItem> inventoryItems)
     {
         this.gameTitle = title;
         this.gameGenre = genre;
         this.gameDescription = description;
         this.player = player;
         this.achievements = achievements;
         this.inventoryItems = inventoryItems;
     }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getGameGenre() {
        return gameGenre;
    }

    public void setGameGenre(String gameGenre) {
        this.gameGenre = gameGenre;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public List<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }

    public void setInventoryItems(List<InventoryItem> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameTitle='" + gameTitle + '\'' +
                ", gameGenre='" + gameGenre + '\'' +
                ", gameDescription='" + gameDescription + '\'' +
                ", player=" + player +
                ", achievements=" + achievements +
                ", inventoryItems=" + inventoryItems +
                '}';
    }
}
