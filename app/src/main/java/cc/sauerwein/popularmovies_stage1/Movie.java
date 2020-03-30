package cc.sauerwein.popularmovies_stage1;

import android.net.Uri;


public class Movie {
    private int id;
    private Uri poster;

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
