package lt.viko.eif.denis.kladijev.marshall.model;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDateTime;

/**
 * Basic model of a player's achievements.
 * The class has fields such as: id, achievementName, achievementDescription, dateAchieved.
 * Also provided links to Player's and Game's classes.
 */

@Entity
@XmlRootElement(name = "Achievement")
@XmlAccessorType(XmlAccessType.FIELD)
public class Achievement
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @XmlElement private String achievementName;
    @XmlElement private String achievementDescription;
    @XmlElement private LocalDateTime dateAchieved;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public Achievement() {}

    public Achievement(String name, String description, LocalDateTime dateAchieved, Player player, Game game)
    {
        this.achievementName = name;
        this.achievementDescription = description;
        this.dateAchieved = dateAchieved;
        this.player = player;
        this.game = game;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return achievementName;
    }

    public void setName(String name) {
        this.achievementName = name;
    }

    public String getDescription() {
        return achievementDescription;
    }

    public void setDescription(String description) {
        this.achievementDescription = description;
    }

    public LocalDateTime getDateAchieved() {
        return dateAchieved;
    }

    public void setDateAchieved(LocalDateTime dateAchieved) {
        this.dateAchieved = dateAchieved;
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
        return "Achievement{" +
                "id=" + id +
                ", name='" + achievementName + '\'' +
                ", description='" + achievementDescription + '\'' +
                ", dateAchieved=" + dateAchieved +
                ", player=" + player +
                ", game=" + game +
                '}';
    }
}
