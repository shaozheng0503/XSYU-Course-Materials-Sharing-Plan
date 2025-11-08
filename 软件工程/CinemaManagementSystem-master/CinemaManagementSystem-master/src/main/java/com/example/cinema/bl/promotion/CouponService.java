package com.example.cinema.bl.promotion;

import com.example.cinema.vo.CouponForm;
import com.example.cinema.vo.PresentForm;
import com.example.cinema.vo.ResponseVO;

/**
 * @date 2019-5-20
 * @author liying,zhm
 */
public interface CouponService {

    /**
     * 获得用户的所有优惠券
     * @param userId 用户Id
     * @return ResponseVo
     */
    ResponseVO getCouponsByUser(int userId);

    /**
     * 获得系统中所有有效的优惠券
     * @return ResponseVo
     */
    ResponseVO getAllCoupons();

    /**
     * 发布优惠券
     * @param couponForm 优惠券Form
     * @return ResponseVo
     */
    ResponseVO addCoupon(CouponForm couponForm);

    /**
     * 为用户添加优惠券
     * @param couponId 优惠券Id
     * @param userId 用户Id
     * @return ResponseVo
     */
    ResponseVO issueCoupon(int couponId,int userId);

    /**
     * 返回所有满足一定消费金额的用户
     * @param totalConsume 消费总金额
     * @return ResponseVo
     */
    ResponseVO getUsersByConsume(double totalConsume);

    /**
     * 将优惠券赠送给用户
     * @param presentForm 赠送Form
     * @return ResponseVo
     */
    ResponseVO presentCoupon2User(PresentForm presentForm);
}
