package dsr.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Seasons")
@Table(name = "seasons")
public class Seasons {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    private Anime animeId;


    @Column(name = "season_number")
    private int seasonNumber;

    @Column(name = "episode_total")
    private int episodeNumber;

    @Column(name = "search_url")
    private String searchUrl;

}
