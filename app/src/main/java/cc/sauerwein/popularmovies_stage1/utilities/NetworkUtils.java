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

    private static final String POPULAR_MOVIES_PATH = "movie/popular";

    private static final String API_KEY_PARAM = "api_key";

    private static final String DEFAULT_IMAGE_SIZE = "w185";


    // For now this function builds a URL which leads to a popular movies API request
    // TODO Make buildUrl more flexible (f.e. it should also be able to create queries for top rated movies
    public static URL buildUrl() {
        Uri builtUri = Uri.parse(API_BASE_URL).buildUpon()
                .appendEncodedPath(POPULAR_MOVIES_PATH)
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
}
