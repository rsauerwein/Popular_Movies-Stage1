package cc.sauerwein.popularmovies_stage1.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import cc.sauerwein.popularmovies_stage1.preferences.ApiKey;

public class NetworkUtils {

    private static final String API_BASE_URL = "https://api.themoviedb.org/3";
    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";

    public static final String OPTION_POPULAR_MOVIES = "movie/popular";
    public static final String OPTION_TOP_RATED_MOVIES_MOVIES = "movie/top_rated";

    private static final String API_KEY_PARAM = "api_key";

    private static final String DEFAULT_IMAGE_SIZE = "w185";


    /**
     * Builds a API request URL
     * Format API_BASE_URL + apiPath + API_KEY
     * Hint: Commonly used apiPaths are stored as public constants within this class
     *
     * @param apiPath example: movie/top_rated
     * @return example: https://api.themoviedb.org/3/movie/top_rated?api_key=xxxx
     */
    public static URL buildUrl(String apiPath) {
        Uri builtUri = Uri.parse(API_BASE_URL).buildUpon()
                .appendEncodedPath(apiPath)
                .appendQueryParameter(API_KEY_PARAM, ApiKey.API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setConnectTimeout(5000);
        urlConnection.setReadTimeout(5000); // set the timeout to 5 seconds
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static Uri createPosterUri(String posterPath) {
        Uri uri = Uri.parse(IMAGE_BASE_URL).buildUpon()
                .appendPath(DEFAULT_IMAGE_SIZE)
                .appendEncodedPath(posterPath)
                .build();

        return uri;
    }
}
