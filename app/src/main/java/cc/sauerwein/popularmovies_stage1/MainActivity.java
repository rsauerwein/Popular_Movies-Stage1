package cc.sauerwein.popularmovies_stage1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.net.URL;

import cc.sauerwein.popularmovies_stage1.utilities.JsonUtils;
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

//        // Testdata //
//        Uri uri = Uri.parse("https://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg");
//
//        Movie fakeMovies[] = new Movie[2];
//        Movie fakemovie = new Movie(1, uri);
//        ;
//        Movie fakemovie1 = new Movie(2, uri);
//        ;
//        fakeMovies[0] = fakemovie;
//        fakeMovies[1] = fakemovie1;
//        mMovieAdapter.setMovieData(fakeMovies);
//        // endTestdata //

        loadMovieData();
    }

    private void loadMovieData() {
        new FetchMovieTask().execute();
    }

    private class FetchMovieTask extends AsyncTask<String, Void, Movie[]> {

        @Override
        protected Movie[] doInBackground(String... strings) {
            URL movieRequestUrl = NetworkUtils.buildUrl(NetworkUtils.PATH_POPULAR_MOVIES);

            try {
                String jsonResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);
                Movie[] movies = JsonUtils.jsonToMovie(jsonResponse);
                return movies;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie[] movies) {
            mMovieAdapter.setMovieData(movies);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_top_rated:
                break;
            case R.id.action_most_popular:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
