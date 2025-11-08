package com.example.cinema.vo;

/**
 * @author hxw
 * @date 2019-6-9
 */
public class LotteryCouponVO {
    /**
     * 需满金额
     */
    private Double targetAmount;
    /**
     * 优惠金额
     */
    private Double discountAmount;
    /**
     * 优惠券名
     */
    private String couponName;

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
}
