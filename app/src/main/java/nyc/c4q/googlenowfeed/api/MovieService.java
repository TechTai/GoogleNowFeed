package nyc.c4q.googlenowfeed.api;

import nyc.c4q.googlenowfeed.MainActivity;
import nyc.c4q.googlenowfeed.model.MovieGenre;
import nyc.c4q.googlenowfeed.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by c4q on 12/16/17.
 */

public interface MovieService {

    @GET("movie/popular?api_key=190c81246de77ceb919643aff221e54d")
    Call<MovieResponse> getMovieResponse();


    @GET("genre/movie/List?api_key=190c81246de77ceb919643aff221e54d")

    //https://api.themoviedb.org/3/genre/movie/list?api_key=190c81246de77ceb919643aff221e54d
    Call<MovieGenre> getMovieGenre();
    //
    @GET("movie/top_rated?api_key=190c81246de77ceb919643aff221e54d")
    Call<MovieResponse> getTopRatedMovies();

    @GET
    Call<MovieResponse> getMovieResponsePages(@Url String path);
}