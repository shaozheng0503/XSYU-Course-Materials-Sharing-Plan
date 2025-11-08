package com.example.cinema.blImpl.promotion.coupon;

import com.example.cinema.po.Coupon;
import com.example.cinema.vo.ResponseVO;

import java.util.List;

public interface CouponServiceForBl {

    ResponseVO getCouponsByUser(int userId);

    ResponseVO issueCoupon(int couponId,int userId);

    ResponseVO getCoupon(int couponId);

    List<Coupon> getAllCoupon();

    boolean deleteCoupon(int couponId,int userId);
}
