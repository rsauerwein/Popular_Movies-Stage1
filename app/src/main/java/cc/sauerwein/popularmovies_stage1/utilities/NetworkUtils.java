package cc.sauerwein.popularmovies_stage1.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import cc.sauerwein.popularmovies_stage1.JsonResponse;
import cc.sauerwein.popularmovies_stage1.preferences.ApiKey;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class NetworkUtils {

    private static final String API_BASE_URL = "https://api.themoviedb.org/3/";
    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";

    public static final String OPTION_POPULAR_MOVIES = "movie/popular";
    public static final String OPTION_TOP_RATED_MOVIES_MOVIES = "movie/top_rated";

    private static final String API_KEY_PARAM = "api_key";
    private static final String API_KEY = ApiKey.API_KEY; //Insert your API key here

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
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static void getPopularMoviesFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetDataService service;
        service = retrofit.create(GetDataService.class);

        Call<JsonResponse> results = service.getPopularMovies(API_KEY);
        results.enqueue(new Callback<JsonResponse>() {

            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                int i = 0;
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
                int i = 0;
            }
        });
    }

    public interface GetDataService {
        @GET("movie/popular")
        Call<JsonResponse> getPopularMovies(@Query("api_key") String api_key);
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

    /**
     * @param posterPath Stored in the Movie object
     * @return URI to the movie poster - for example http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
     */
    public static Uri createPosterUri(String posterPath) {

        return Uri.parse(IMAGE_BASE_URL).buildUpon()
                .appendPath(DEFAULT_IMAGE_SIZE)
                .appendEncodedPath(posterPath)
                .build();
    }
}
