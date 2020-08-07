package dsr.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Seasons")
@Table(name = "seasons")
public class Seasons {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    private Anime anime;


    @Column(name = "season_number")
    private int seasonNumber;

    @Column(name = "episode_total")
    private int episodeTotal;

    @Column(name = "search_url")
    private String searchUrl;

    public Seasons(Anime anime,int seasonNumber, int episodeTotal, String searchUrl) {
        this.anime = anime;
        this.seasonNumber = seasonNumber;
        this.episodeTotal = episodeTotal;
        this.searchUrl = searchUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Anime getAnimeId() {
        return anime;
    }

    public void setAnimeId(Anime animeId) {
        this.anime = animeId;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getEpisodeTotal() {
        return episodeTotal;
    }

    public void setEpisodeTotal(int episodeTotal) {
        this.episodeTotal = episodeTotal;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }
}
