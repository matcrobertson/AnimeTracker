package dsr.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Anime")
@Table(name = "anime")
@NoArgsConstructor
@AllArgsConstructor
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UserAnime> usersAnime =  new HashSet<>();

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Seasons> animeSeasons =  new HashSet<>();

    @Column(name = "image_url")
    private String imageUrl;

    public Anime(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public Anime(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<UserAnime> getUsersAnime() {
        return usersAnime;
    }

    public void setUsersAnime(Set<UserAnime> usersAnime) {
        this.usersAnime = usersAnime;
    }

    public Set<Seasons> getAnimeSeasons() {
        return animeSeasons;
    }

    public void setAnimeSeasons(Set<Seasons> animeSeasons) {
        this.animeSeasons = animeSeasons;
    }
}
