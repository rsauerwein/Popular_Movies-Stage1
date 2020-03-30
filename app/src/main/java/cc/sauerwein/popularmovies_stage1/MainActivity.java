package cc.sauerwein.popularmovies_stage1;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.net.URL;

import cc.sauerwein.popularmovies_stage1.utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_movie_overview);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mMovieAdapter = new MovieAdapter();

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mMovieAdapter);

        // Testdata //
        Uri uri = Uri.parse("https://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg");

        Movie fakeMovies[] = new Movie[2];
        Movie fakemovie = new Movie(1, uri);
        ;
        Movie fakemovie1 = new Movie(2, uri);
        ;
        fakeMovies[0] = fakemovie;
        fakeMovies[1] = fakemovie1;
        mMovieAdapter.setMovieData(fakeMovies);
        // endTestdata //

        loadMovieData();
    }

    private void loadMovieData() {
        // TODO temp disabled
        // new FetchMovieTask().execute();
    }

    private class FetchMovieTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            URL movieRequestUrl = NetworkUtils.buildUrl();

            try {
                String jsonResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);
                return jsonResponse;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }
}
