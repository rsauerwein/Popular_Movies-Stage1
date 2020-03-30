package cc.sauerwein.popularmovies_stage1.utilities;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import cc.sauerwein.popularmovies_stage1.Movie;

public class JsonUtils {
    // Takes as input the http response from https://api.themoviedb.org/3/movie/popular
    public static Movie[] jsonToMovie(String apiResponse) {
        Movie[] movies = null;
        try {
            JSONObject moviesJson = new JSONObject(apiResponse);

            // Get the movie results from JSON and convert them into Movie objects
            String results = moviesJson.getString("results");
            Gson gson = new Gson();
            movies = gson.fromJson(results, Movie[].class);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }
}
