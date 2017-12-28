package nyc.c4q.googlenowfeed.activities;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.googlenowfeed.R;
import nyc.c4q.googlenowfeed.model.Genre;

public class DetailActivity extends AppCompatActivity {
    TextView nameOfMovie, overView, userRating, releaseDate, duration, genre, popularity;
    ImageView movieImage, movieCirculeImgae;
    CollapsingToolbarLayout collapsingToolbar;
    AppBarLayout appBarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setupView();
        setupDate();

    }

    private void setupDate() {
        Intent gettingData = getIntent();
        if (gettingData.hasExtra("title")) {

            String name = getIntent().getExtras().getString("title");
            String image = getIntent().getExtras().getString("movieImage");
            String circuleImage = getIntent().getExtras().getString("circleImage");
            String relase = getIntent().getExtras().getString("relase");
            String rating = getIntent().getExtras().getString("rating");
           // String durationtTime = getIntent().getExtras().getString("duration");
           // double popularityNum = getIntent().getExtras().getDouble("popularity");
            String overViewStory = getIntent().getExtras().getString("overView");
          //  ArrayList<Genre> genreList = (ArrayList<Genre>) getIntent().getSerializableExtra("genre");
            nameOfMovie.setText(name);
            Picasso.with(this).load(image).into(movieImage);
            Picasso.with(this).load(circuleImage).into(movieCirculeImgae);
            releaseDate.setText(relase);
            userRating.setText(rating);
           // duration.setText(durationtTime);
           // popularity.setText(""+(popularityNum * 100));
            overView.setText(overViewStory);
            /*
            String nameGen = "";
            for (int i = 0; i < genreList.size(); i++) {
                nameGen = nameGen + "," + genreList.get(i).getName();
            }
            genre.setText(nameGen);
            */


        } else {
            Toast.makeText(this, "No data from Api", Toast.LENGTH_SHORT).show();
        }


    }

    private void setupView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initCollapsingToolbar();

        movieImage = (ImageView) findViewById(R.id.thumbnail_image_header);
        movieCirculeImgae = (ImageView) findViewById(R.id.image_film);
        nameOfMovie = (TextView) findViewById(R.id.movie_name);
        overView = (TextView) findViewById(R.id.overview_info);
        userRating = (TextView) findViewById(R.id.text_rating);
        releaseDate = (TextView) findViewById(R.id.relase_info);
        duration = (TextView) findViewById(R.id.duration_info);
        genre = (TextView) findViewById(R.id.genre_info);
        popularity = (TextView) findViewById(R.id.vote_num);

    }


    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.movie_details));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

//    @Override
//    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
//        youTubePlayer.setPlaybackEventListener(playbackEventListener);
//    }
//
//    @Override
//    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//    }
//
//    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
//        @Override
//        public void onLoading() {
//
//        }
//
//        @Override
//        public void onLoaded(String s) {
//
//        }
//
//        @Override
//        public void onAdStarted() {
//
//        }
//
//        @Override
//        public void onVideoStarted() {
//
//        }
//
//        @Override
//        public void onVideoEnded() {
//
//        }
//
//        @Override
//        public void onError(YouTubePlayer.ErrorReason errorReason) {
//
//        }
//    };
//    private YouTubePlayer.PlaybackEventListener playbackEventListener=new YouTubePlayer.PlaybackEventListener() {
//        @Override
//        public void onPlaying() {
//
//        }
//
//        @Override
//        public void onPaused() {
//
//        }
//
//        @Override
//        public void onStopped() {
//
//        }
//
//        @Override
//        public void onBuffering(boolean b) {
//
//        }
//
//        @Override
//        public void onSeekTo(int i) {
//
//        }
//    };

    /*
    <com.google.android.youtube.player.YouTubePlayerView
    android:id="@+id/player_video"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="#fff"
            ></com.google.android.youtube.player.YouTubePlayerView>
*/
    //
}
