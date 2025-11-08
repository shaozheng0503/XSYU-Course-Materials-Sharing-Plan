package com.example.cinema.data.promotion;

import com.example.cinema.po.CardType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @date 2019-5-20
 * @author zhm
 */
@Mapper
public interface CardTypeMapper {

    /**
     * 返回所有会员卡类型
     * @return List<CardType>
     */
    List<CardType> selectAllCards();

    /**
     * 发布新的会员卡类型
     * @param cardType 会员卡类型PO
     * @return int
     */
    int insertOneCard(CardType cardType);

    /**
     * 删除会员卡类型
     * @param cardId 会员卡Id
     * @return int
     */
    int deleteCardById(int cardId);

    /**
     * 更新会员卡类型信息
     * @param cardId 会员卡Id
     * @param cardType 会员卡类型PO
     * @return int
     */
    int updateCardById(int cardId, CardType cardType);

    /**
     * 根据会员卡类型Id查找会员卡类型
     * @param id 会员卡类型Id
     * @return CardType
     */
    CardType selectCardById(int id);

}
