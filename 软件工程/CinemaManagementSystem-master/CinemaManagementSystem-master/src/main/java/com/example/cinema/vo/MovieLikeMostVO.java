package com.example.cinema.vo;

/**
 * @author hxw
 * @date 2019-6-6
 */
public class MovieLikeMostVO {
    /**
     * 电影id
     */
    private Integer movieId;
    /**
     * 指定时间段内总票房
     */
    private Integer boxOffice;
    /**
     * 电影名称
     */
    private String name;
    /**
     * 电影海报Url
     */
    private String posterUrl;

    public Integer getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(Integer boxOffice) {
        this.boxOffice = boxOffice;
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

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
}
