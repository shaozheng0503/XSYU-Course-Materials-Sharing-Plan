package com.example.cinema.data.sales;

import com.example.cinema.po.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@Mapper
public interface TicketMapper {

    int insertTicket(Ticket ticket);

    int insertTickets(List<Ticket> tickets);

    int deleteTicket(int ticketId);

    int deleteLockedTicket(@Param("userId") int userId, @Param("scheduleId") int scheduleId);

    int updateTicketState(@Param("ticketId") int ticketId, @Param("state") int state);

    int updateTicketActualPay(@Param("ticketId") int ticketId, @Param("actualPay") double actualPay);

    List<Ticket> selectTicketsBySchedule(int scheduleId);

    List<Ticket> selectTicketsByUserAndSchedule(@Param("userId") int userId, @Param("scheduleId") int scheduleId);

    Ticket selectTicketByScheduleIdAndSeat(@Param("scheduleId") int scheduleId, @Param("column") int columnIndex, @Param("row") int rowIndex);

    Ticket selectTicketById(int id);

    List<Ticket> selectTicketByUser(int userId);

    int selectTicketNumBySchedule(@Param("scheduleId") int scheduleId);

    @Scheduled(cron = "0 */1 * * * ?")
    void cleanExpiredTicket();

    int insertTicketAndConsume(int ticketId, int consumeId);
}

