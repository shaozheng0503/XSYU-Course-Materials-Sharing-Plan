package com.example.cinema.vo;

import com.example.cinema.po.Hall;

/**
 * @author fjj
 * @date 2019/4/11 3:46 PM
 */
public class HallVO {
    private Integer id;
    private String name;
    private int[][] seats;
    private int scale;

    public HallVO() {
    }

    public HallVO(Hall hall){
        this.id = hall.getId();
        this.name = hall.getName();
        this.seats = hall.getParsedSeats();
        this.scale = hall.getScale();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[][] getSeats() {
        return seats;
    }

    public void setSeats(int[][] seats) {
        this.seats = seats;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
}
