package nyc.c4q.googlenowfeed.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.googlenowfeed.R;
import nyc.c4q.googlenowfeed.model.Movies;

/**
 * Created by c4q on 12/23/17.
 */

public class Movieadapter extends RecyclerView.Adapter<MovieHolder> {
    List<Movies> movieList = new ArrayList<>();


    public Movieadapter(List<Movies> movieList) {
        this.movieList = movieList;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_view, parent, false);

        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {

        holder.onBind(movieList.get(position));
    }

    @Override
    public int getItemCount() {

        return null != movieList ? movieList.size() : 0;
    }


}
