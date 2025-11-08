package com.example.cinema.vo;

/**
 * 电影想看人数VO
 *
 * @author steve
 */
public class MovieLikeVO {
    /**
     * 电影id
     */
    private Integer movieId;
    /**
     * 想看人数总数
     */
    private Integer likeCount;
    /**
     * 电影名称
     */
    private String name;
    /**
     * 电影海报Url
     */
    private String posterUrl;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}
