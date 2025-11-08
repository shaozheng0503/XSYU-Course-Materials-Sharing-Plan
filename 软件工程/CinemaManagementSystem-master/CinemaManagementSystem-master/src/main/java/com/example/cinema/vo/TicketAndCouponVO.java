package com.example.cinema.vo;

import java.util.List;

public class TicketAndCouponVO {
    private List<Integer> ticketId;
    private int couponId;

    public List<Integer> getTicketId() {
        return ticketId;
    }

    public void setTicketId(List<Integer> ticketId) {
        this.ticketId = ticketId;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }
}