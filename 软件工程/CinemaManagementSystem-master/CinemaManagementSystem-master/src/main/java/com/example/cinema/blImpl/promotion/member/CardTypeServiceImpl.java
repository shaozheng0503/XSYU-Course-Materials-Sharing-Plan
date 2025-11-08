package com.example.cinema.blImpl.promotion.member;

import com.example.cinema.bl.promotion.CardTypeService;
import com.example.cinema.data.promotion.CardTypeMapper;
import com.example.cinema.po.CardType;
import com.example.cinema.vo.CardTypeForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @date 2019-5-20
 * @author zhm
 */
@Service
public class CardTypeServiceImpl implements CardTypeService {
    @Autowired
    CardTypeMapper cardTypeMapper;

    @Override
    public ResponseVO getCards() {
        try {
            return ResponseVO.buildSuccess(cardTypeMapper.selectAllCards());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    @Transactional
    public ResponseVO publishCard(CardTypeForm cardTypeForm) {
        CardType cardType = new CardType(cardTypeForm);
        if (cardTypeForm.getDescription()==null || cardTypeForm.getDescription().equals("")){
            cardType.setDescription("充值会员卡满"+cardTypeForm.getTopUpTarget()+"送"+cardTypeForm.getTopUpDiscount());
        }
        try {
            return ResponseVO.buildSuccess(cardTypeMapper.insertOneCard(cardType));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    @Transactional
    public ResponseVO deleteCard(int cardId) {
        try {
            return ResponseVO.buildSuccess(cardTypeMapper.deleteCardById(cardId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    @Transactional
    public ResponseVO updateCard(int cardId, CardTypeForm cardTypeForm) {
        CardType cardType = new CardType(cardTypeForm);
        try {
            return ResponseVO.buildSuccess(cardTypeMapper.updateCardById(cardId, cardType));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
}
