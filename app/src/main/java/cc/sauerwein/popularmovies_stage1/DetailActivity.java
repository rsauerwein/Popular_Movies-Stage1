package cc.sauerwein.popularmovies_stage1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {

    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle(getString(R.string.movie_detail));

        Intent intent = getIntent();
        String json = intent.getStringExtra(Intent.EXTRA_TEXT);
        mMovie = new Gson().fromJson(json, Movie.class);
    }
}
