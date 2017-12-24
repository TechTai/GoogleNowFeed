package nyc.c4q.googlenowfeed.model;

import java.util.ArrayList;

/**
 * Created by c4q on 12/16/17.
 */

public class MovieResponse {
    private int page;
    private int total_results;
    private int total_pages;
    private Movies movies;


    private ArrayList<Movies> results;

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return this.total_results;
    }

    public void setTotalResults(int total_results) {
        this.total_results = total_results;
    }

    public int getTotalPages() {
        return this.total_pages;
    }

    public void setTotalPages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<Movies> getResults() {
        return this.results;
    }

    public void setResults(ArrayList<Movies> results) {
        this.results = results;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }
}