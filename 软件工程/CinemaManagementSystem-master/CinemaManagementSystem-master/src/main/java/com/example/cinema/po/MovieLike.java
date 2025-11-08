package com.example.cinema.po;

/**
 * 电影想看人数pojo
 *
 * @author steve
 */
public class MovieLike {
    private int movieId;
    private int count;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
