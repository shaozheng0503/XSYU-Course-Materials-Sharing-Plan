package com.example.cinema.vo;

/**
 * @Date:   2019-5-14
 * @Author: hxw
 * @Info:   电影上座率VO
 */
public class PlacingRateVO {
    /**
     * 电影名称
     */
    private String name;
    /**
     * 上座率
     */
    private double placingRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPlacingRate() {
        return placingRate;
    }

    public void setPlacingRate(double placingRate) {
        this.placingRate = placingRate;
    }
}
