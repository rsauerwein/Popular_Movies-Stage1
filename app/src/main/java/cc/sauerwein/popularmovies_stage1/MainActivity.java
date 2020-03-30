package cc.sauerwein.popularmovies_stage1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.URL;

import cc.sauerwein.popularmovies_stage1.utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadMovieData();
    }

    private void loadMovieData() {
        new FetchMovieTask().execute();
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
            TextView tv = findViewById(R.id.tv_test);
            tv.setText(s);
        }
    }
}
