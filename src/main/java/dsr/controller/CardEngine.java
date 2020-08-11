package dsr.controller;

import dsr.entity.AnimeCards;
import dsr.entity.Seasons;
import dsr.entity.User;
import dsr.entity.UserAnime;
import dsr.persistence.GenericDao;

import java.util.ArrayList;

public class CardEngine {
    public ArrayList<AnimeCards> makeCards(int id) {
        ArrayList<AnimeCards> animeCards = new ArrayList<>();
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User user = userDao.getById(id);
        ArrayList<UserAnime> usersAnimeList = new ArrayList<>(user.getUsersAnime());
        for(UserAnime anime: usersAnimeList) {
            AnimeCards card = new AnimeCards();
            int seasonsWatched = anime.getSeasonNumber();
            int currentUserEpisode = anime.getEpisodeNumber();
            //set the userAnime Id
            card.setUserAnimeId(anime.getId());
            //set the link
            card.setLastUpdatedLink(anime.getUserAnimeLink());

            //set the name
            card.setAnimeName(anime.getAnime().getTitle());

            int animeEpisodeTotal = 0;
            int userAnimeEpisodeTotal = 0;
            for(Seasons season: anime.getAnime().getAnimeSeasons()){
                 animeEpisodeTotal += season.getEpisodeTotal();
                 if(season.getSeasonNumber() < seasonsWatched) {
                     userAnimeEpisodeTotal += season.getEpisodeTotal();
                 }
            }
            userAnimeEpisodeTotal += currentUserEpisode;
            //sets the total episode count
            card.setTotalEpisodes(animeEpisodeTotal);

            //sets the episodes watched by the user
            card.setEpisodesWatched(userAnimeEpisodeTotal);

            animeCards.add(card);
        }
        return animeCards;
    }
}
