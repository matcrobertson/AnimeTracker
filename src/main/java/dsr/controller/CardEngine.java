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
            card.setImageUrl(anime.getAnime().getImageUrl());

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

    public ArrayList<AnimeCards> searchCards(String searchTerm, int userId) {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        ArrayList<AnimeCards> animeCards = new ArrayList<>();
        User user = userDao.getById(userId);
        ArrayList<UserAnime> userAnimes = new ArrayList<>(user.getUsersAnime());
        for(UserAnime userAnime: userAnimes) {
            if(userAnime.getAnime().getTitle().contains(searchTerm)) {
                AnimeCards card = new AnimeCards();
                card.setAnimeName(userAnime.getAnime().getTitle());
                card.setImageUrl(userAnime.getAnime().getImageUrl());
                card.setUserAnimeId(userAnime.getId());
                card.setLastUpdatedLink(userAnime.getUserAnimeLink());

                int animeEpisodeTotal = 0;
                int episodeTotal = 0;
                int seasonsWatched = userAnime.getSeasonNumber();
                int currentUserEpisode = userAnime.getEpisodeNumber();
                for(Seasons season: userAnime.getAnime().getAnimeSeasons()){
                    animeEpisodeTotal += season.getEpisodeTotal();
                    if(season.getSeasonNumber() < seasonsWatched) {
                        episodeTotal += season.getEpisodeTotal();
                    }
                }
                episodeTotal += currentUserEpisode;
                card.setTotalEpisodes(animeEpisodeTotal);
                card.setEpisodesWatched(episodeTotal);
                animeCards.add(card);
            }
        }
        return animeCards;
    }
}
