package com.example.cinema.bl.consume;

import com.example.cinema.vo.ResponseVO;

import java.sql.Timestamp;

/**
 * @author hxw
 * @date 2019-5-21
 */
public interface ConsumeService {

    /**
     * 获取用户全部的充值记录
     * @param userId
     * @return
     */
    ResponseVO getAllTopUpHistory(Integer userId);

    /**
     * 获取简略消费记录信息
     * @param userId
     * @return
     */
    ResponseVO getBriefConsumeHis(Integer userId);

    /**
     * 获取消费记录详细信息
     * @param id
     * @return
     */
    ResponseVO getConsumeHisDetail(Integer id);

    /**
     * 添加充值记录
     * @param userId
     * @param money
     * @param discount
     * @param balance
     * @param time
     * @return
     */
    ResponseVO addTopUpHistory(Integer userId, Double money, Double discount, Double balance, Timestamp time);

    /**
     * 添加消费记录
     * @param userId
     * @param money
     * @param discount
     * @param consumeType
     * @param type
     * @param contentId
     * @return
     */
    ResponseVO addConsumeHistory(Integer userId, Double money, Double discount, String consumeType,
                                 Integer type, Integer contentId);

    /**
     * 获取消费总额满一定值的用户信息
     * @param totalConsume
     * @return
     */
    ResponseVO getConsumeQualifiedUsers(Double totalConsume);

}
