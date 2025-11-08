package com.example.cinema.data.consume;

import com.example.cinema.po.ConsumeHistory;
import com.example.cinema.po.TopUpHistory;
import com.example.cinema.vo.TotalConsumeUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author hxw
 * @date 2019-5-21
 */
@Mapper
public interface ConsumeMapper {

    /**
     * 获取指定用户的全部充值记录
     * @param userId
     * @return
     */
    List<TopUpHistory> getTopUpHistoryByUserId(@Param("userId") Integer userId);

    /**
     * 获取指定用户的全部充值记录
     * @param userId
     * @return
     */
    List<ConsumeHistory> getConsumeHistoryByUserId(@Param("userId") Integer userId);

    /**
     * 根据id获取消费记录
     * @param id
     * @return
     */
    ConsumeHistory getConsumeHistoryById(@Param("id") Integer id);

    /**
     * 插入充值记录
     * @param userId
     * @param money
     * @param discount
     * @param balance
     * @param time
     * @return
     */
    int insertTopUpHistory(@Param("user_id") Integer userId, @Param("money") Double money,
                           @Param("discount") Double discount, @Param("balance") Double balance,
                           @Param("time") Timestamp time);

    /**
     * 插入消费记录
     * @param userId
     * @param money
     * @param discount
     * @param consumeType
     * @param type
     * @param contentId
     * @return
     */
    int insertConsumeHistory(@Param("user_id") Integer userId, @Param("money") Double money,
                             @Param("discount") Double discount, @Param("consumeType") String consumeType,
                             @Param("type") Integer type, @Param("contentId") Integer contentId);

    /**
     * 获取消费总额满一定数额的用户信息
     * @param totalConsume
     * @return
     */
    List<TotalConsumeUser> selectConsumeQulifiedUsers(@Param("totalConsume") Double totalConsume);

    /**
     * 获取某张电影票所对应的消费记录
     * @param ticketId
     * @return
     */
    int getConsumeHistoryByTicketId(@Param("ticketId") Integer ticketId);

    List<Integer> getTicketIdsByConsumeId(int consumeId);
}
