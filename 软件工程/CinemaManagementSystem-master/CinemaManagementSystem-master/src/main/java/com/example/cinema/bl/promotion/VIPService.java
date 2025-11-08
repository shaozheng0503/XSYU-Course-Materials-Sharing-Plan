package com.example.cinema.bl.promotion;

import com.example.cinema.po.VIPCard;
import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.vo.ResponseVO;



/**
 * @date 2019-5-20
 * @author liying,zhm
 */
public interface VIPService {

    /**
     * 为用户添加会员卡
     * @param userId 用户Id
     * @param cardTypeId 会员卡类型Id
     * @return ResponseVo
     */
    ResponseVO addVIPCard(int userId, int cardTypeId);

    /**
     * 根据会员卡Id查找会员卡
     * @param id 会员卡Id
     * @return ResponseVo
     */
    ResponseVO getCardById(int id);

    /**
     * 充值会员卡
     * @param vipCardForm 会员卡Form
     * @return ResponseVo
     */
    ResponseVO charge(VIPCardForm vipCardForm);

    /**
     * 根据用户Id查找会员卡
     * @param userId 用户Id
     * @return ResponseVo
     */
    ResponseVO getCardByUserId(int userId);

    /**
     * 为用户更换会员卡类型
     * @param cardId 会员卡Id
     * @param cardTypeId 会员卡类型Id
     * @return ResponseVo
     */
    ResponseVO changeVIPCard(int cardId, int cardTypeId);

    /**
     * 根据会员卡Id返回会员卡
     * @param id 会员卡Id
     * @return VIPCard
     */
    VIPCard getSingleCard(int id);
}
