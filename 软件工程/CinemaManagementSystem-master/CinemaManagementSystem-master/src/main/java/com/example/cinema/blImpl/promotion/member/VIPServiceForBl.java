package com.example.cinema.blImpl.promotion.member;

import com.example.cinema.vo.ResponseVO;

public interface VIPServiceForBl {

    ResponseVO getCardByUserId(int userId);

    ResponseVO payByCard(int id, double balance);

}
