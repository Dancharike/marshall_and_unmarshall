package lt.viko.eif.denis.kladijev.marshall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lt.viko.eif.denis.kladijev.marshall.utility.LongToStringAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic model of a player that includes: id, nickname, email, level, experience of a player.
 * Additionally, there are Lists of player's: games, achievements and inventory items.
 * Also, this class calculates total amount of player's achievements and items.
 */

@Entity
@XmlRootElement(name = "Player")
@XmlAccessorType(XmlAccessType.FIELD)
public class Player
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlJavaTypeAdapter(LongToStringAdapter.class)
    @XmlID
    @XmlAttribute(name = "id")
    private Long id;
    @XmlElement private String nickName;
    @XmlElement private String email;
    @XmlElement private int level;
    @XmlElement private int experience;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    @XmlElementWrapper(name = "Games")
    @XmlElement(name = "Game")
    @JsonManagedReference
    private List<Game> games = new ArrayList<>();

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    @XmlElementWrapper(name = "Achievements")
    @XmlElement(name = "Achievement")
    @JsonIgnore
    private List<Achievement> achievements = new ArrayList<>();

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    @XmlElementWrapper(name = "Inventory")
    @XmlElement(name = "InventoryItem")
    @JsonIgnore
    private List<InventoryItem> inventory = new ArrayList<>();

    /**
     * Public method for calculating total amount of player's achievements.
     * @return total number of achievements.
     */

    @Transient
    @XmlElement
    @JsonProperty("totalAchievements")
    public int getTotalAchievements()
    {
        int total = 0;
        total += (achievements != null) ? achievements.size() : 0;

        for(Game game : games)
        {
            total += (game.getAchievements() != null) ? game.getAchievements().size() : 0;
        }
        return total;
    }

    /**
     * Public method for calculating total amount of player's items.
     * @return total number of items.
     */

    @Transient
    @XmlElement
    @JsonProperty("totalInventoryItems")
    public int getTotalInventoryItems()
    {
        int total = 0;
        total += (inventory != null) ? inventory.size() : 0;

        for(Game game : games)
        {
            total += (game.getInventoryItems() != null) ? game.getInventoryItems().size() : 0;
        }
        return total;
    }

    public Player() {}

    public Player(String nickName, String email, int level, int experience)
    {
        this.nickName = nickName;
        this.email = email;
        this.level = level;
        this.experience = experience;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public List<InventoryItem> getInventory() {
        return inventory;
    }

    public void setInventory(List<InventoryItem> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", level=" + level +
                ", experience=" + experience +
                ", games=" + games +
                ", achievements=" + achievements +
                ", inventory=" + inventory +
                '}';
    }
}
