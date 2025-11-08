package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.vo.PresentForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liying on 2019/4/16.
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    @GetMapping("{userId}/get")
    public ResponseVO getCoupons(@PathVariable int userId){
        return couponService.getCouponsByUser(userId);
    }

    @PostMapping("present")
    public ResponseVO presentCoupon2User(@RequestBody PresentForm presentForm){
        return couponService.presentCoupon2User(presentForm);
    }

    @GetMapping("get/coupons")
    public ResponseVO getAllCoupons(){
        return couponService.getAllCoupons();
    }

    @GetMapping("get/users")
    public ResponseVO getUsersByConsume(@RequestParam double totalConsume) {
        return couponService.getUsersByConsume(totalConsume);
    }

}
