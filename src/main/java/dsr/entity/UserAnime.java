package dsr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "UserAnime")
@Table(name = "user_anime")
public class UserAnime {

//    use one to many in place of many-to-many since there is additional information on the table
    @Column(name = "user_id")
    private int userId;

    @Column(name = "anime_id")
    private int animeId;

    @Column(name = "season_number")
    private int seasonNumber;

    @Column(name = "episode_number")
    private int episodeNumber;

    @Column(name = "user_anime_link")
    private String userAnimeLink;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAnimeId() {
        return animeId;
    }

    public void setAnimeId(int animeId) {
        this.animeId = animeId;
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
