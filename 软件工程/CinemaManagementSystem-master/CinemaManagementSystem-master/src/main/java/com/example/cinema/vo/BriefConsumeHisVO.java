package com.example.cinema.vo;

import java.sql.Timestamp;

/**
 * @author hxw
 * @date 2019-5-22
 */
public class BriefConsumeHisVO {
    /**
     * id
     */
    private Integer id;
    /**
     * 消费金额
     */
    private Double money;
    /**
     * 优惠金额
     */
    private Double discount;
    /**
     * 消费类型
     */
    private String type;
    /**
     * 消费方式
     */
    private String consumeType;
    /**
     * 消费时间
     */
    private Timestamp time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(String consumeType) {
        this.consumeType = consumeType;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
