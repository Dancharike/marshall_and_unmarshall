package lt.viko.eif.denis.kladijev.marshall.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lt.viko.eif.denis.kladijev.marshall.utility.LocalDateTimeAdapter;

import java.time.LocalDateTime;

/**
 * Basic model of a player's achievements.
 * The class has fields such as: id, achievementName, achievementDescription, dateAchieved.
 * Also provided links to Player's and Game's classes.
 */

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Achievement.class)
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
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateAchieved;

    @ManyToOne
    @JoinColumn(name = "player_id")
    @XmlIDREF
    @JsonIdentityReference(alwaysAsId = true)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @XmlIDREF
    @JsonIdentityReference(alwaysAsId = true)
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

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String name) {
        this.achievementName = name;
    }

    public String getAchievementDescription() {
        return achievementDescription;
    }

    public void setAchievementDescription(String description) {
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
