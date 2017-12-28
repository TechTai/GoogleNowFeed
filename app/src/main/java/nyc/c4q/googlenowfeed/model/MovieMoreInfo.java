package nyc.c4q.googlenowfeed.model;

import java.util.ArrayList;

/**
 * Created by Muaadh Melhi
 */

public class MovieMoreInfo {

    private int budget;
    private ArrayList<Genre> genres;
    private String homepage;
    private int revenue;
    private int runtime;
    private ArrayList<SpokenLanguage> spoken_languages;
    private double popularity;


    public int getBudget() {
        return this.budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }


    public ArrayList<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }


    public String getHomepage() {
        return this.homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }


    public int getRevenue() {
        return this.revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }


    public int getRuntime() {
        return this.runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }


    public ArrayList<SpokenLanguage> getSpokenLanguages() {
        return this.spoken_languages;
    }

    public void setSpokenLanguages(ArrayList<SpokenLanguage> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public double getPopularity() {
        return this.popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }


    public class SpokenLanguage {
        private String iso_639_1;

        public String getIso6391() {
            return this.iso_639_1;
        }

        public void setIso6391(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
