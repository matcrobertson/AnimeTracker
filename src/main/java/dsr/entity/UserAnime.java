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


}