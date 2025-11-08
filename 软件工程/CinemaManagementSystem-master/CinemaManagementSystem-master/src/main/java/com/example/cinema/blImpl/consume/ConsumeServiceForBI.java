package com.example.cinema.blImpl.consume;

/**
 * @author cz
 * @date 2020-12-8
 */
public interface ConsumeServiceForBI {

    /**
     * 获取某张电影票所对应的消费记录
     * @param ticketId
     * @return
     */
    int getConsumeByTicketId(int ticketId);

}
