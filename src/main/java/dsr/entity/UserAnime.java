package dsr.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "UserAnime")
@Table(name = "user_anime")
public class UserAnime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    //    use one to many in place of many-to-many since there is additional information on the table
    @ManyToOne
    private Anime anime;

    @ManyToOne
    private User user;

    @Column(name = "season_number")
    private int seasonNumber;

    @Column(name = "episode_number")
    private int episodeNumber;

    @Column(name = "user_anime_link")
    private String userAnimeLink;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getUserAnimeLink() {
        return userAnimeLink;
    }

    public void setUserAnimeLink(String userAnimeLink) {
        this.userAnimeLink = userAnimeLink;
    }
}