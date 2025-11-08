package com.example.cinema.bl.sales;

import com.example.cinema.vo.RefundForm;
import com.example.cinema.vo.ResponseVO;

/**
 * @date 2019-5-18
 * @author CZ
 */

public interface RefundService {

    /**
     * 用于获取所有退票策略
     * @return
     */
    ResponseVO getAllRefund();

    /**
     * 用于添加一个新的退票策略
     * @param refundForm
     * @return
     */
    ResponseVO publishRefund(RefundForm refundForm);

    /**
     * 用于删除退票策略
     * @param refundId
     * @return
     */
    ResponseVO deleteRefund(int refundId);

    /**
     * 用于获取指定退票策略
     * @param refundId
     * @return
     */
    ResponseVO getRefundById(int refundId);
}
