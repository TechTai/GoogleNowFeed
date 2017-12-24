package nyc.c4q.googlenowfeed;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.googlenowfeed.adapter.EndlessRecyclerViewScrollListener;
import nyc.c4q.googlenowfeed.adapter.Movieadapter;
import nyc.c4q.googlenowfeed.api.MovieService;
import nyc.c4q.googlenowfeed.model.MovieResponse;
import nyc.c4q.googlenowfeed.model.Movies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
        movieRetrofit();

        movieadapter = new Movieadapter(moviesList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(movieadapter);

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
        Call<MovieResponse> call = service.getMovieResponsePages(pathUrl);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                moviesList.addAll(response.body().getResults());
                movieadapter.notifyItemRangeInserted(movieadapter.getItemCount(), moviesList.size());

                swipeLayout.setRefreshing(false);


                Log.d(TAG, "onResponse: " + moviesList);
                Log.d(TAG, "onResponse: <<< size: " + moviesList.size() + " >>>");
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

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


}

