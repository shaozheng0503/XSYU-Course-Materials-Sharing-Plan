package com.example.cinema.blImpl.promotion.member;

import com.example.cinema.bl.consume.ConsumeService;
import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.data.promotion.VIPCardMapper;
import com.example.cinema.po.ConsumeHistory;
import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.po.VIPCard;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


/**
 * Created by liying on 2019/4/14.
 */
@Service
public class VIPServiceImpl implements VIPService, VIPServiceForBl {
    @Autowired
    VIPCardMapper vipCardMapper;

    @Autowired
    ConsumeService consumeService;

    @Override
    public ResponseVO addVIPCard(int userId, int cardTypeId) {
        VIPCard vipCard = new VIPCard();
        vipCard.setUserId(userId);
        vipCard.setCardTypeId(cardTypeId);
        vipCard.setBalance(0);
        try {
            vipCardMapper.insertOneCard(vipCard);
            VIPCard vip =  vipCardMapper.selectCardById(vipCard.getId());
            //添加消费记录,传入卡底价和优惠金额
            consumeService.addConsumeHistory(userId, vip.getCardType().getPrice(),0.0,"银行卡", ConsumeHistory.BUY_VIP_CARD, vipCard.getId());
            return ResponseVO.buildSuccess(vip);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getCardById(int id) {
        try {
            return ResponseVO.buildSuccess(vipCardMapper.selectCardById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }


    @Override
    public ResponseVO charge(VIPCardForm vipCardForm) {

        VIPCard vipCard = vipCardMapper.selectCardById(vipCardForm.getVipId());
        if (vipCard == null) {
            return ResponseVO.buildFailure("会员卡不存在");
        }
        double balance = vipCard.calculateTopUpDiscount(vipCardForm.getAmount());
        vipCard.setBalance(vipCard.getBalance() + balance);
        try {
            vipCardMapper.updateCardBalance(vipCardForm.getVipId(), vipCard.getBalance());
            //添加充值记录
            consumeService.addTopUpHistory(vipCard.getUserId(),balance,balance-vipCardForm.getAmount(),
                    vipCard.getBalance(), new Timestamp(System.currentTimeMillis()));
            return ResponseVO.buildSuccess(vipCard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getCardByUserId(int userId) {
        try {
            VIPCard vipCard = vipCardMapper.selectCardByUserId(userId);
            if(vipCard==null){
                return ResponseVO.buildFailure("用户卡不存在");
            }
            return ResponseVO.buildSuccess(vipCard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO payByCard(int id, double balance) {
        try {
            vipCardMapper.updateCardBalance(id,balance);
            return  ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public VIPCard getSingleCard(int id) {
        return vipCardMapper.selectSingleCard(id);
    }

    @Override
    public ResponseVO changeVIPCard(int cardId, int cardTypeId) {
        try {
            VIPCard oldCard = vipCardMapper.selectCardById(cardId);
            vipCardMapper.deleteCardById(cardId);

            VIPCard vipCard = new VIPCard();
            vipCard.setUserId(oldCard.getUserId());
            vipCard.setCardTypeId(cardTypeId);
            vipCard.setBalance(0);
            vipCardMapper.insertOneCard(vipCard);
            VIPCard newCard =  vipCardMapper.selectCardById(vipCard.getId());
            consumeService.addConsumeHistory(oldCard.getUserId(), newCard.getCardType().getPrice(),0.0,"银行卡", ConsumeHistory.BUY_VIP_CARD, newCard.getId());

            vipCardMapper.updateCardBalance(newCard.getId(),oldCard.getBalance() * 0.8);
            return  ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
}
