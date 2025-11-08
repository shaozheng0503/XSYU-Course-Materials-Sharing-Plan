package com.example.cinema.data.promotion;

import com.example.cinema.po.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @date 2019-5-20
 * @author liying, zhm
 */
@Mapper
public interface CouponMapper {

    /**
     * 发布优惠券
     * @param coupon 优惠券Id
     * @return int
     */
    int insertCoupon(Coupon coupon);

    /**
     * 查找用户的所有优惠券
     * @param userId 用户Id
     * @return List<Coupon>
     */
    List<Coupon> selectCouponByUser(int userId);

    /**
     * 查找系统中所有在有效期内的优惠券
     * @return List<Coupon>
     */
    List<Coupon> selectAllCoupons();

    /**
     * 根据优惠券Id查找优惠券
     * @param id 优惠券Id
     * @return Coupon
     */
    Coupon selectById(int id);

    /**
     * 赠送优惠券给用户
     * @param couponId 优惠券Id
     * @param userId 用户Id
     */
    void insertCouponUser(@Param("couponId") int couponId,@Param("userId")int userId);

    /**
     * 删除用户的优惠券
     * @param couponId 优惠券Id
     * @param userId 用户Id
     * @return int
     */
    int deleteCouponUser(@Param("couponId") int couponId,@Param("userId")int userId);

    /**
     * 查找用户拥有的满足一定金额的优惠券
     * @param userId 用户Id
     * @param amount 金额
     * @return List<Coupon>
     */
    List<Coupon> selectCouponByUserAndAmount(@Param("userId") int userId,@Param("amount") double amount);
}
