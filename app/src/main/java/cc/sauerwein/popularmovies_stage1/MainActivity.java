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

    private MenuItem mMostPopular;
    private MenuItem mTopRated;

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

        loadMovieData(NetworkUtils.OPTION_POPULAR_MOVIES);
    }

    private void loadMovieData(String option) {
        new FetchMovieTask().execute(option);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        mMostPopular = menu.findItem(R.id.action_most_popular);
        mTopRated = menu.findItem(R.id.action_top_rated);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_top_rated:
                mMovieAdapter.setMovieData(null);
                loadMovieData(NetworkUtils.OPTION_TOP_RATED_MOVIES_MOVIES);
                mTopRated.setVisible(false);
                mMostPopular.setVisible(true);
                break;
            case R.id.action_most_popular:
                mMovieAdapter.setMovieData(null);
                loadMovieData(NetworkUtils.OPTION_POPULAR_MOVIES);
                mMostPopular.setVisible(false);
                mTopRated.setVisible(true);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private class FetchMovieTask extends AsyncTask<String, Void, Movie[]> {

        @Override
        protected Movie[] doInBackground(String... strings) {
            URL movieRequestUrl = NetworkUtils.buildUrl(strings[0]);

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
}
