package com.example.cinema.vo;

/**
 * @date 2019-5-23
 * @author zhm
 */
public class PresentForm {
    /**
     * 被赠送的用户
     */
    private int[] userIds;

    /**
     * 赠送的优惠券
     */
    private int[] couponIds;

    public int[] getUserIds() {
        return userIds;
    }

    public void setUserIds(int[] userIds) {
        this.userIds = userIds;
    }

    public int[] getCouponIds() {
        return couponIds;
    }

    public void setCouponIds(int[] couponIds) {
        this.couponIds = couponIds;
    }
}
