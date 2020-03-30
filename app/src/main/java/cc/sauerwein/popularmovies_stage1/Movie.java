package cc.sauerwein.popularmovies_stage1;

import com.google.gson.annotations.SerializedName;


public class Movie {
    private int id;
    private String title;
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("poster_path")
    private String posterPath;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
