package com.example.cinema.vo;

import com.example.cinema.po.Coupon;

import java.util.List;

public class PayInfo {

    private List<TicketToPayVO> ticketList;
    private List<Coupon> coupons;

    public List<TicketToPayVO> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<TicketToPayVO> ticketList) {
        this.ticketList = ticketList;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
