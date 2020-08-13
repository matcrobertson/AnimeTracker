package dsr.entity;

public class AnimeCards {
    private int episodesWatched;
    private int totalEpisodes;
    private String animeName;
    private String lastUpdatedLink;
    private int userAnimeId;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getUserAnimeId() {
        return userAnimeId;
    }

    public void setUserAnimeId(int userAnimeId) {
        this.userAnimeId = userAnimeId;
    }

    public int getEpisodesWatched() {
        return episodesWatched;
    }

    public void setEpisodesWatched(int episodesWatched) {
        this.episodesWatched = episodesWatched;
    }

    public int getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setTotalEpisodes(int totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public String getLastUpdatedLink() {
        return lastUpdatedLink;
    }

    public void setLastUpdatedLink(String lastUpdatedLink) {
        this.lastUpdatedLink = lastUpdatedLink;
    }
}
