package com.example.cinema.vo;

/**
 * @author hxw
 * @date 2019-5-25
 */
public class TotalConsumeUser {
    /**
     * 用户id
     */
    private int userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 消费总额
     */
    private Double total;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
