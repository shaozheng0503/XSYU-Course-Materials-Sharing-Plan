package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.sales.LotteryService;
import com.example.cinema.blImpl.promotion.coupon.CouponServiceForBl;
import com.example.cinema.po.Coupon;
import com.example.cinema.vo.LotteryCouponVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LotteryServiceImpl implements LotteryService{

    @Autowired
    CouponServiceForBl couponServiceForBl;

    @Override
    public ResponseVO getLottery(Double min, Double max, int num, Integer userId) {
        try {
            //先进行扣款,由于使用67无敌银行卡,默认扣款成功
            //抽取优惠券
            List<Coupon> couponList = couponServiceForBl.getAllCoupon();
            List<Coupon> qualifiedCoupon = new ArrayList<>();
            Double sum = 0.0;   //优惠金额总计
            for (Coupon coupon : couponList) {
                if (coupon.getDiscountAmount() >= min && coupon.getDiscountAmount() <= max) {
                    qualifiedCoupon.add(coupon);
                    sum += coupon.getDiscountAmount();
                }
            }
            if (qualifiedCoupon.size() == 0) {
                return ResponseVO.buildFailure("没有符合条件的优惠券");
            }
            List<Coupon> lotteryPool = new ArrayList<>();
            for (Coupon coupon : qualifiedCoupon) {
                //由优惠金额总计除以优惠金额得到权重(与被抽到的概率成正相关)
                int weight = (int) (sum / coupon.getDiscountAmount());
                //通过各种方法使优惠金额低的优惠券权重增加
                Double dis = coupon.getDiscountAmount();
                weight *= (int) (max - dis + 1);
                for (int i = 1; i <qualifiedCoupon.size(); i++) {
                    if ( dis <= (sum/i) ) {
                        weight *= 5;
                    }
                }
                for (int i = 0; i < weight; i++) {
                    lotteryPool.add(coupon);
                }
            }
            List<LotteryCouponVO> res = new ArrayList<>();
            //十连必出优惠金额最高的优惠券
            boolean flag = false;
            if (num != 10) {
                flag = true;
            }
            for (int i = 0; i < num; i++) {
                int rd = (int) (Math.random() * lotteryPool.size());
                Coupon coupon = lotteryPool.get(rd);
                if (coupon.getDiscountAmount() == findMaxCoupon(qualifiedCoupon).getDiscountAmount()) {
                    flag = true;
                }
                if (i == num-1 && !flag) {
                    coupon = findMaxCoupon(qualifiedCoupon);
                }
                couponServiceForBl.issueCoupon(coupon.getId(), userId);
                LotteryCouponVO vo = new LotteryCouponVO();
                vo.setCouponName(coupon.getDescription());
                vo.setTargetAmount(coupon.getTargetAmount());
                vo.setDiscountAmount(coupon.getDiscountAmount());
                //避免优惠金额最高的优惠券总是出现在最后
                if (i == num-1 && !flag) {
                    int index = (int)(Math.random()*num);
                    index = index-1 < 0 ? 0 : index-1;
                    res.add(index,vo);
                } else {
                    res.add(vo);
                }
            }
            return ResponseVO.buildSuccess(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("幸运轮盘受到神秘力量干扰!");
        }
    }

    /**
     * 返回优惠金额最高的Coupon
     * @param coupons
     * @return
     */
    private Coupon findMaxCoupon(List<Coupon> coupons) {
        int flag = 0;
        Double max = 0.0;
        for (int i = 0; i < coupons.size(); i++) {
            if (coupons.get(i).getDiscountAmount() >= max) {
                flag = i;
                max = coupons.get(i).getDiscountAmount();
            }
        }
        return coupons.get(flag);
    }
}
