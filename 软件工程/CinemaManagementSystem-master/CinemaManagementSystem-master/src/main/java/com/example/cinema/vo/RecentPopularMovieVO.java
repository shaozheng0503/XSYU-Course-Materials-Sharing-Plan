package com.example.cinema.vo;

/**
 * @Date:   2019-5-3
 * @Author: hxw
 * @Info:   近期最受欢迎电影VO
 */
public class RecentPopularMovieVO {
    /**
     * 电影名称
     */
    private String name;
    /**
     * 近期总票房
     */
    private Integer boxOffice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(Integer boxOffice) {
        this.boxOffice = boxOffice;
    }
}
