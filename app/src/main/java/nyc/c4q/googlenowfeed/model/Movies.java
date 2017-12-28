package nyc.c4q.googlenowfeed.model;

import java.util.ArrayList;

/**
 * Created by c4q on 12/16/17.
 */

public class Movies {
    private int vote_count;
    private int id;
    private boolean video;
    private double vote_average;
    private String title;
    private String poster_path;
    private String original_language;
    private String original_title;
    private ArrayList<Integer> genre_ids;
    private String backdrop_path;
    private String overview;
    private String release_date;
    private ArrayList<MovieGenre> genres;
    private MovieMoreInfo moreInfo;



    public int getVoteCount() {
        return this.vote_count;
    }

    public void setVoteCount(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getVideo() {
        return this.video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return this.vote_average;
    }

    public void setVoteAverage(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        String path = "https://image.tmdb.org/t/p/w500" + poster_path;
        return path;
    }

    public void setPosterPath(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginalLanguage() {
        return this.original_language;
    }

    public void setOriginalLanguage(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginalTitle() {
        return this.original_title;
    }

    public void setOriginalTitle(String original_title) {
        this.original_title = original_title;
    }

    public ArrayList<Integer> getGenreIds() {
        return this.genre_ids;
    }

    public void setGenreIds(ArrayList<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getBackdropPath() {
        String backPath = "https://image.tmdb.org/t/p/w500" + backdrop_path;
        return backPath;
    }

    public void setBackdropPath(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return this.overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return this.release_date;
    }

    public void setReleaseDate(String release_date) {
        this.release_date = release_date;
    }

    public ArrayList<MovieGenre> getGenres() {
        return this.genres;
    }

    public void setGenres(ArrayList<MovieGenre> genres) {
        this.genres = genres;
    }


    public MovieMoreInfo getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(MovieMoreInfo moreInfo) {
        this.moreInfo = moreInfo;
    }
}