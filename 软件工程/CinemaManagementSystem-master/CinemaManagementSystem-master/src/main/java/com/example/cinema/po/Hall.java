package com.example.cinema.po;

import com.google.gson.Gson;

/**
 * @author fjj
 * @date 2019/4/28 5:09 PM
 */
public class Hall {
    // 该位置有座位
    public static final int HALL_SEAT_VALID = 0;
    // 该位置无座位
    public static final int HALL_SEAT_INVALID = -1;
    // 影厅规格
    public static final int HALL_SCALE_LARGE = 0;
    public static final int HALL_SCALE_MEDIAN = 1;
    public static final int HALL_SCALE_SMALL = 2;

    private Integer id;
    private String name;
    private String seats;
    private Integer scale;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    // 将json格式的座位信息解析成int二维数组
    public int[][] getParsedSeats(){
        return new Gson().fromJson(this.getSeats(), int[][].class);
    }

    // 将解析后的座位重新生成json
    public void setGeneratedSeats(int[][] seats){
        this.setSeats(new Gson().toJson(seats));
    }

    //获得影厅可用座位总数
    public int getSeatsNum() {
        int[][] seats = getParsedSeats();
        int sum = 0;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                sum += (seats[i][j] + 1);
            }
        }
        return sum;
    }
}
