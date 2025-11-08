package com.example.cinema.po;

import java.sql.Timestamp;

/**
 * @author hxw
 * @date 2019-5-21
 */
public class TopUpHistory {
    /**
     * id
     */
    private Integer id;
    /**
     * 账户id
     */
    private Integer userId;
    /**
     * 充值金额
     */
    private Double money;
    /**
     * 优惠金额
     */
    private Double discount;
    /**
     * 充值后余额
     */
    private Double balance;
    /**
     * 充值时间
     */
    private Timestamp time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
