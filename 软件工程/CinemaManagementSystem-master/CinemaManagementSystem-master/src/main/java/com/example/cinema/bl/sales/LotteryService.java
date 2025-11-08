package com.example.cinema.bl.sales;

import com.example.cinema.vo.ResponseVO;

/**
 * @author hxw
 * @date 2019-6-9
 */
public interface LotteryService {

    /**
     * 抽取优惠金额min~max区间的num张优惠券
     * @param min
     * @param max
     * @param num
     * @param userId
     * @return
     */
    ResponseVO getLottery(Double min, Double max, int num, Integer userId);

}
