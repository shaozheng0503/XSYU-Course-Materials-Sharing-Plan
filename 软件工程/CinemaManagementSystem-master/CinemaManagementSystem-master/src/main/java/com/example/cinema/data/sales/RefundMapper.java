package com.example.cinema.data.sales;

import com.example.cinema.po.Refund;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @date 2019-5-19
 * @author CZ
 */
@Mapper
public interface RefundMapper {

    List<Refund> selectRefund();

    int insertRefund(Refund refund);

    int deleteRefundById(int refundId);

    List<Refund> selectRefundByMovieId(int movieId);

    Refund selectRefundById(int refundId);
}
