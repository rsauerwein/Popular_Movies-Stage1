package cc.sauerwein.popularmovies_stage1;

import android.net.Uri;


public class Movie {
    private int id;
    private Uri poster;
    private String poster_path;

    public Movie() {

    }

    public Movie(int id, String poster_path) {
        this.id = id;
        this.poster_path = poster_path;
    }

    public Movie(int id, Uri poster) {
        this.id = id;
        this.poster = poster;
    }

    public int getId() {
        return id;
    }

    public Uri getPoster() {
        return poster;
    }
}
