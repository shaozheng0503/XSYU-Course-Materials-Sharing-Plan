package com.example.cinema.blImpl.consume;

import com.example.cinema.bl.consume.ConsumeService;
import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.blImpl.management.hall.HallServiceForBl;
import com.example.cinema.blImpl.management.movie.MovieServiceForBl;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.blImpl.promotion.member.VIPServiceForBl;
import com.example.cinema.blImpl.sales.TicketServiceForBl;
import com.example.cinema.data.consume.ConsumeMapper;
import com.example.cinema.data.management.HallMapper;
import com.example.cinema.data.management.MovieMapper;
import com.example.cinema.data.management.ScheduleMapper;
import com.example.cinema.data.promotion.VIPCardMapper;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.*;
import com.example.cinema.vo.BriefConsumeHisVO;
import com.example.cinema.vo.BuyCardHistoryVO;
import com.example.cinema.vo.BuyTicketHistoryVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hxw
 * @date 2019-5-21
 */
@Service
public class ConsumeServiceImpl implements ConsumeService, ConsumeServiceForBI{

    @Autowired
    private ConsumeMapper consumeMapper;
    @Autowired
    private TicketServiceForBl ticketServiceForBl;
    @Autowired
    private VIPService vipService;
    @Autowired
    private ScheduleServiceForBl scheduleServiceForBl;
    @Autowired
    private MovieServiceForBl movieServiceForBl;
    @Autowired
    private HallServiceForBl hallServiceForBl;

    @Override
    public ResponseVO getAllTopUpHistory(Integer userId) {
        try {
            return ResponseVO.buildSuccess(consumeMapper.getTopUpHistoryByUserId(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("获取充值记录失败!");
        }
    }

    @Override
    public ResponseVO getBriefConsumeHis(Integer userId) {
        try {
            List<ConsumeHistory> histories= consumeMapper.getConsumeHistoryByUserId(userId);
            List<BriefConsumeHisVO> res = new ArrayList<>();
            for (ConsumeHistory history : histories) {
                BriefConsumeHisVO vo = new BriefConsumeHisVO();
                vo.setId(history.getId());
                vo.setMoney(history.getMoney());
                vo.setType(getTypeStr(history.getType()));
                vo.setConsumeType(history.getConsumeType());
                vo.setTime(getConsumeTime(history));
                res.add(vo);
            }
            return ResponseVO.buildSuccess(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("获取消费记录失败!");
        }
    }

    @Override
    public ResponseVO getConsumeHisDetail(Integer id) {
        try {
            ConsumeHistory history = consumeMapper.getConsumeHistoryById(id);
            if (history.getType() == ConsumeHistory.BUY_TICKET) {
                BuyTicketHistoryVO vo = new BuyTicketHistoryVO();
                vo.setId(history.getId());
                vo.setMoney(history.getMoney());
                vo.setDiscount(history.getDiscount());
                vo.setType(getTypeStr(history.getType()));
                vo.setConsumeType(history.getConsumeType());
                List<Integer> ticketIds=consumeMapper.getTicketIdsByConsumeId(history.getId());
                Ticket ticket=new Ticket();
                String seat="";
                for(int ticketId:ticketIds){
                    ticket = ticketServiceForBl.getTicketById(ticketId);
                    seat=seat+(ticket.getRowIndex()+1)+"排"+(ticket.getColumnIndex()+1)+"座, ";
                }
                vo.setColumnIndex(ticket.getColumnIndex());
                vo.setRowIndex(ticket.getRowIndex());
                vo.setSeat(seat.substring(0,seat.length()-2));
                vo.setTime(ticket.getTime());
                ScheduleItem schedule = scheduleServiceForBl.getScheduleItemById(ticket.getScheduleId());
                vo.setStartTime(schedule.getStartTime());
                vo.setMovieName(movieServiceForBl.getMovieById(schedule.getMovieId()).getName());
                vo.setHallName(hallServiceForBl.getHallById(schedule.getHallId()).getName());
                return ResponseVO.buildSuccess(vo);
            } else if (history.getType() == ConsumeHistory.BUY_VIP_CARD) {
                BuyCardHistoryVO vo =new BuyCardHistoryVO();
                vo.setId(history.getId());
                vo.setMoney(history.getMoney());
                vo.setDiscount(history.getDiscount());
                vo.setType(getTypeStr(history.getType()));
                vo.setConsumeType(history.getConsumeType());
                vo.setTime(getConsumeTime(history));
                VIPCard card = vipService.getSingleCard(history.getContentId());
                vo.setCardType(card.getCardType().getName());
                return ResponseVO.buildSuccess(vo);
            } else if (history.getType() == ConsumeHistory.BUY_LOTTERY) {
                return ResponseVO.buildSuccess();
            } else {
                return ResponseVO.buildFailure("消费类型错误!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("获取详细信息失败!");
        }
    }

    @Override
    public ResponseVO addTopUpHistory(Integer userId, Double money, Double discount, Double balance, Timestamp time) {
        try {
            if (consumeMapper.insertTopUpHistory(userId, money, discount, balance, time) == 1) {
                return ResponseVO.buildSuccess();
            } else {
                return ResponseVO.buildFailure("添加充值记录失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("添加充值记录失败!");
        }
    }

    @Override
    public ResponseVO addConsumeHistory(Integer userId, Double money, Double discount,
                                        String consumeType, Integer type, Integer contentId) {
        try {
            if (consumeMapper.insertConsumeHistory(userId, money, discount, consumeType, type, contentId) == 1) {
                return ResponseVO.buildSuccess();
            } else {
                return ResponseVO.buildFailure("添加消费记录失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("添加消费记录失败!");
        }
    }

    @Override
    public ResponseVO getConsumeQualifiedUsers(Double totalConsume) {
        try {
            return ResponseVO.buildSuccess(consumeMapper.selectConsumeQulifiedUsers(totalConsume));
        } catch (Exception e) {
            return ResponseVO.buildFailure("获取用户失败!");
        }
    }

    /**
     * 获取消费类型字符串
     * @param type
     * @return
     */
    private String getTypeStr(Integer type) {
        //无法使用switch
        if (type == ConsumeHistory.BUY_TICKET) {
            return ConsumeHistory.BUY_TICKET_STR;
        } else if (type == ConsumeHistory.BUY_VIP_CARD) {
            return ConsumeHistory.BUY_VIP_CARD_STR;
        } else if (type == ConsumeHistory.BUY_LOTTERY) {
            return ConsumeHistory.BUY_LOTTERY_STR;
        } else {
            return "";
        }
    }

    /**
     * 获取消费时间
     * @param his
     * @return
     */
    private Timestamp getConsumeTime(ConsumeHistory his) {
        if (his.getType() == ConsumeHistory.BUY_TICKET) {
            return ticketServiceForBl.getTicketById(his.getContentId()).getTime();
        } else if (his.getType() == ConsumeHistory.BUY_VIP_CARD) {
            return vipService.getSingleCard(his.getContentId()).getJoinDate();
        } else {
            return null;
        }
    }

    @Override
    public int getConsumeByTicketId(int ticketId) {
        try{
            return consumeMapper.getConsumeHistoryByTicketId(ticketId);
        } catch (Exception e) {
            return 0;
        }
    }
}
