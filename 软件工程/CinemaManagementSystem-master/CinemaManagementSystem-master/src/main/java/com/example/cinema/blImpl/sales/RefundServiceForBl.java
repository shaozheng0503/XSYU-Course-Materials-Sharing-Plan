package com.example.cinema.blImpl.sales;

import com.example.cinema.po.Refund;

import java.util.List;

public interface RefundServiceForBl {

    List<Refund> getRefundByMovieId(int movieId);

}
