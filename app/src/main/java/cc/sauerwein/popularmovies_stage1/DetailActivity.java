package cc.sauerwein.popularmovies_stage1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import cc.sauerwein.popularmovies_stage1.utilities.NetworkUtils;

public class DetailActivity extends AppCompatActivity {

    private Movie mMovie;

    private TextView mMovieTitleTv;
    private TextView mMovieDescriptionTv;
    private TextView mUserRating;
    private TextView mReleaseDate;
    private ImageView mMovieThumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle(getString(R.string.movie_detail));

        Intent intent = getIntent();
        String json = intent.getStringExtra(Intent.EXTRA_TEXT);
        mMovie = new Gson().fromJson(json, Movie.class);

        mMovieTitleTv = findViewById(R.id.tv_movie_title);
        mMovieDescriptionTv = findViewById(R.id.tv_description);
        mUserRating = findViewById(R.id.tv_user_rating);
        mReleaseDate = findViewById(R.id.tv_release_date);

        populateUI();
    }

    private void populateUI() {
        mMovieTitleTv.setText(mMovie.getTitle());
        mMovieDescriptionTv.setText(mMovie.getOverview());
        mUserRating.setText(mMovie.getUserRating() + "/10");
        mReleaseDate.setText(mMovie.getReleaseDate());
        mMovieThumbnail = findViewById(R.id.iv_movie_poster_thumbnail);

        String posterPath = mMovie.getPosterPath();
        Uri posterUri = NetworkUtils.createPosterUri(posterPath);
        Picasso.get().load(posterUri).into(mMovieThumbnail);

    }
}
