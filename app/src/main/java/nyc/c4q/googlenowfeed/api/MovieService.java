package nyc.c4q.googlenowfeed.api;

import nyc.c4q.googlenowfeed.model.MovieGenre;
import nyc.c4q.googlenowfeed.model.MovieMoreInfo;
import nyc.c4q.googlenowfeed.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by c4q on 12/16/17.
 */

public interface MovieService {

    final static String API_KEY = "190c81246de77ceb919643aff221e54d";

    /**
     * @param path Popluar MovieResponsePages youl will get info about the popular movie
     */
    @GET
    Call<MovieResponse> getMovieResponsePages(@Url String path);

    //https://api.themoviedb.org/3/search/movie?api_key=<<api_key>>&language=en-US&page=1&include_adult=false
    //https://api.themoviedb.org/3/search/movie?api_key=###&query=tron

    /**
     * @param pathSearch for the trailer , you will get the key the value then use it on
     *                   https://www.youtube.com/watch?v={keyValue}
     */
    //http://api.themoviedb.org/3/movie/157336/videos?api_key=190c81246de77ceb919643aff221e54d
    //http://api.themoviedb.org/3/movie/157336?api_key=190c81246de77ceb919643aff221e54d&append_to_response=videos
    // https://api.themoviedb.org/3/movie/{movie_id}/videos?api_key=<<api_key>>&language=en-US
    @GET
    Call<MovieResponse> getMovieSearchResponse(@Url String pathSearch);


    //    https://api.themoviedb.org/3/movie/550?api_key=190c81246de77ceb919643aff221e54d
    @GET("movie/{id}?api_key=" + API_KEY)
    Call<MovieMoreInfo> getMoreInfo(@Path("id") int id);


    //https://api.themoviedb.org/3/genre/movie/list?api_key=190c81246de77ceb919643aff221e54d
    @GET("genre/movie/List?api_key=" + API_KEY)
    Call<MovieGenre> getMovieGenre();


}