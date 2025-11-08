package com.example.cinema.data.promotion;

import com.example.cinema.po.VIPCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @date 2019-5-20
 * @author liying, zhm
 */
@Mapper
public interface VIPCardMapper {

    /**
     * 为用户添加会员卡
     * @param vipCard 会员卡PO
     * @return int
     */
    int insertOneCard(VIPCard vipCard);

    /**
     * 根据会员卡Id删除用户的会员卡
     * @param cardId 会员卡Id
     * @return int
     */
    int deleteCardById(int cardId);

    /**
     * 根据会员卡Id查找用户的会员卡
     * @param id 会员卡Id
     * @return VIPCard
     */
    VIPCard selectCardById(int id);

    /**
     * 更新会员卡的余额
     * @param id 会员卡Id
     * @param balance 余额
     */
    void updateCardBalance(@Param("id") int id,@Param("balance") double balance);

    /**
     * 查找用户的会员卡
     * @param userId 用户Id
     * @return VIPCard
     */
    VIPCard selectCardByUserId(int userId);

    /**
     * 根据会员卡Id查找用户的会员卡
     * @param id 会员卡Id
     * @return VIPCard
     */
    VIPCard selectSingleCard(@Param("id") int id);
}
