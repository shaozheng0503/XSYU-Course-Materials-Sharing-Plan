package com.example.cinema.bl.promotion;

import com.example.cinema.vo.CardTypeForm;
import com.example.cinema.vo.ResponseVO;

/**
 * @date 2019-5-20
 * @author zhm
 */
public interface CardTypeService {
    /**
     * 获取所有会员卡类型
     * @return ResponseVo
     */
    ResponseVO getCards();

    /**
     * 发布会员卡
     * @param cardTypeForm 会员卡类型Form
     * @return ResponseVo
     */
    ResponseVO publishCard(CardTypeForm cardTypeForm);

    /**
     * 删除会员卡类型
     * @param cardId 会员卡Id
     * @return ResponseVo
     */
    ResponseVO deleteCard(int cardId);

    /**
     * 更新会员卡类型
     * @param cardId 会员卡Id
     * @param cardTypeForm 会员卡类型Form
     * @return ResponseVo
     */
    ResponseVO updateCard(int cardId, CardTypeForm cardTypeForm);
}
