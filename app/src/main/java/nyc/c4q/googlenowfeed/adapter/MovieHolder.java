package nyc.c4q.googlenowfeed.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import nyc.c4q.googlenowfeed.R;
import nyc.c4q.googlenowfeed.activities.DetailActivity;
import nyc.c4q.googlenowfeed.activities.MainActivity;
import nyc.c4q.googlenowfeed.model.MovieMoreInfo;
import nyc.c4q.googlenowfeed.model.Movies;

import static android.content.ContentValues.TAG;

/**
 * Created by c4q on 12/23/17.
 */

class MovieHolder extends RecyclerView.ViewHolder {
    private ImageView moviePic;
    private RatingBar ratingBar;
    private TextView movieName;
    private float movieRate;
    private Movies movie;


    public MovieHolder(View itemView) {
        super(itemView);
        moviePic = itemView.findViewById(R.id.pic);
        ratingBar = itemView.findViewById(R.id.rating_bar);
        movieName = itemView.findViewById(R.id.movie_title);

    }

    public void onBind(final Movies movies) {
        this.movie = movies;

        Picasso.with(itemView.getContext()).load(movies.getPosterPath()).into(moviePic);
        movieRate = (float) movies.getVoteAverage();
        movieName.setText(movies.getOriginalTitle());
        ratingBar.setRating((movieRate * 5) / 10);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "You clicked " + movie.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("title", movie.getTitle());
                intent.putExtra("movieImage", movie.getBackdropPath());
                intent.putExtra("circleImage", movie.getPosterPath());
                intent.putExtra("relase", movie.getReleaseDate());
                intent.putExtra("rating", movie.getVoteAverage());
                intent.putExtra("overView", movie.getOverview());
                new MainActivity().getMoreInfo(movie.getId());


                view.getContext().startActivity(intent);


            }
        });

    }
}
