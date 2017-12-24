package nyc.c4q.googlenowfeed.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nyc.c4q.googlenowfeed.R;
import nyc.c4q.googlenowfeed.model.Movies;

/**
 * Created by c4q on 12/23/17.
 */

class MovieHolder extends RecyclerView.ViewHolder {
    private ImageView moviePic;
    private RatingBar ratingBar;
    private TextView movieName;
    private float movieRate;

    public MovieHolder(View itemView) {
        super(itemView);
        moviePic = itemView.findViewById(R.id.pic);
        ratingBar = itemView.findViewById(R.id.rating_bar);
        movieName=itemView.findViewById(R.id.movie_title);

    }

    public void onBind(Movies movies) {

        Picasso.with(itemView.getContext()).load(movies.getPosterPath()).into(moviePic);
        movieRate= (float) movies.getVoteAverage();
        movieName.setText(movies.getOriginalTitle());
        ratingBar.setRating((movieRate*5)/10);

    }
}
