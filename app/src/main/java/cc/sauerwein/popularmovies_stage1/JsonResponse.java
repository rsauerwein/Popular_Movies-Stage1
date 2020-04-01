package cc.sauerwein.popularmovies_stage1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonResponse {
    @SerializedName("results")
    private List<Movie> mMovies;
}
