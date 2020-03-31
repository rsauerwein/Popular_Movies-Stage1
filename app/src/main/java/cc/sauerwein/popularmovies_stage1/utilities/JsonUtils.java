package cc.sauerwein.popularmovies_stage1.utilities;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import cc.sauerwein.popularmovies_stage1.Movie;

public class JsonUtils {
    /**
     * Converts the API response from TheMovieDB (for example from https://api.themoviedb.org/3/movie/popular)
     * into Movie objects
     *
     * @param apiResponse response from TheMovieDB API with movie search results
     * @return Array with movies
     */
    // Takes as input the http response from
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
