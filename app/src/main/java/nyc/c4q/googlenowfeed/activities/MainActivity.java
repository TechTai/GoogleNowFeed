package nyc.c4q.googlenowfeed.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import nyc.c4q.googlenowfeed.R;
import nyc.c4q.googlenowfeed.adapter.EndlessRecyclerViewScrollListener;
import nyc.c4q.googlenowfeed.adapter.Movieadapter;
import nyc.c4q.googlenowfeed.api.MovieService;
import nyc.c4q.googlenowfeed.model.MovieMoreInfo;
import nyc.c4q.googlenowfeed.model.MovieResponse;
import nyc.c4q.googlenowfeed.model.Movies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private String TAG = MainActivity.class.getSimpleName();
    private final String BASE_URL = "https://api.themoviedb.org/3/";
    private SwipeRefreshLayout swipeLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Movieadapter movieadapter;
    private ProgressBar itemProgressbar;
    private Retrofit retrofit;
    private MovieService service;
    private int pageNumber = 1;
    private String pathUrl;
    private List<Movies> moviesList = new ArrayList<>();
    private boolean isLoading = false;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initApp();
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initApp();
                Toast.makeText(MainActivity.this, "Movies Refreshed", Toast.LENGTH_SHORT).show();
                swipeLayout.setRefreshing(false);
            }
        });


        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, final RecyclerView view) {
                pageNumber++;
                isLoading = true;
                updateTheList(isLoading);
            }
        });

        getUserMoviePages(pageNumber);


    }

    private void initApp() {
        setup();
        movieRetrofit();
         intent = new Intent(MainActivity.this, DetailActivity.class);
        movieadapter = new Movieadapter(moviesList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(movieadapter);

    }

    private void updateTheList(boolean isLoading) {
        if (isLoading) {
            itemProgressbar.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    getUserMoviePages(pageNumber);
                }
            }, 2000);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    itemProgressbar.setVisibility(View.GONE);
                }
            }, 1200);
        }
    }


    private void getUserMoviePages(int pageNumber) {
        pathUrl = "movie/popular?api_key=190c81246de77ceb919643aff221e54d&page=" + pageNumber;
        Log.d(TAG, "getUserMoviePages:  <<<< has been calling >>>>");
        Call<MovieResponse> call = service.getMovieResponsePages(pathUrl);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                moviesList.addAll(response.body().getResults());
                movieadapter.notifyItemRangeInserted(movieadapter.getItemCount(), moviesList.size());

                swipeLayout.setRefreshing(false);


                Log.d(TAG, "onResponse: <<< size: " + moviesList.size() + " >>>");
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    //
    public void getMoreInfo(int movieId) {
        pathUrl = ("movie/" + movieId + "?api_key=190c81246de77ceb919643aff221e54d");
        Log.d(TAG, "getMoreInfo:  <<<< has been calling >>>>");
        Log.d(TAG, "getMoreInfo: " + pathUrl);
        movieRetrofit();
        Call<MovieMoreInfo> callInfo = service.getMoreInfo(pathUrl);
        Log.d(TAG, "getMoreInfo: " + callInfo.toString());
        callInfo.enqueue(new Callback<MovieMoreInfo>() {
            @Override
            public void onResponse(Call<MovieMoreInfo> call, Response<MovieMoreInfo> response) {
                MovieMoreInfo movieMoreInfo = response.body();
                Log.d(TAG, "onClick:MainActivity " + movieMoreInfo.getRuntime());
                Log.d(TAG, "onClick:MainActivity " + movieMoreInfo.getPopularity());
                Log.d(TAG, "onClick:MainActivity " + movieMoreInfo.getGenres().get(0).getName());

//                intent.putExtra("duration", movieMoreInfo.getRuntime());
//                intent.putExtra("popularity", movieMoreInfo.getPopularity());
//                intent.putExtra("genre", movieMoreInfo.getGenres());
//                getApplicationContext().startActivity(intent);

            }

            @Override
            public void onFailure(Call<MovieMoreInfo> call, Throwable t) {
                Log.d(TAG, "onFailure:calling movieMoreInfo ");
            }
        });

    }

    //

    private void setup() {
        swipeLayout = findViewById(R.id.main_content);
        itemProgressbar = findViewById(R.id.item_progress_bar);
        recyclerView = findViewById(R.id.recyle_view);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
    }

    private void movieRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(MovieService.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);


        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        if (!newText.isEmpty()) {
            getUserSearch(newText);
        } else {
            moviesList = new ArrayList<>();
            getUserMoviePages(pageNumber);

        }


        return true;
    }

    private void getUserSearch(String keyword) {
        //https://api.themoviedb.org/3/search/movie?api_key=190c81246de77ceb919643aff221e54d&query=
        pathUrl = "search/movie?api_key=190c81246de77ceb919643aff221e54d&query=" + keyword;
        Log.d(TAG, "getSearchResponse:  <<<< has been calling >>>>");
        Call<MovieResponse> call = service.getMovieSearchResponse(pathUrl);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                moviesList = new ArrayList<>();
                moviesList = response.body().getResults();
                movieadapter.setFilter(moviesList);

                Log.d(TAG, "onResponse: <<< size: " + moviesList.size() + " >>>");
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });


    }
}

