package lt.viko.eif.denis.kladijev.marshall.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Achievement
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDateTime dateAchieved;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public Achievement() {}

    public Achievement(String title, String description, LocalDateTime dateAchieved, Player player)
    {
        this.title = title;
        this.description = description;
        this.dateAchieved = dateAchieved;
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "Achievement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dateAchieved=" + dateAchieved +
                ", player=" + player +
                '}';
    }
}
