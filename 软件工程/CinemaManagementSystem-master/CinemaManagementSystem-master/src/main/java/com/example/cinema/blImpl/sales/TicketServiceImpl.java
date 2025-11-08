package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.consume.ConsumeService;
import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.blImpl.consume.ConsumeServiceForBI;
import com.example.cinema.blImpl.management.hall.HallServiceForBl;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.blImpl.promotion.activity.ActivityServiceForBl;
import com.example.cinema.blImpl.promotion.coupon.CouponServiceForBl;
import com.example.cinema.blImpl.promotion.member.VIPServiceForBl;
import com.example.cinema.data.management.ScheduleMapper;
import com.example.cinema.data.promotion.CouponMapper;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.Coupon;
import com.example.cinema.po.Hall;
import com.example.cinema.po.ScheduleItem;
import com.example.cinema.po.Ticket;
import com.example.cinema.po.VIPCard;
import com.example.cinema.po.ConsumeHistory;
import com.example.cinema.po.Activity;
import com.example.cinema.po.Refund;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@Service
public class TicketServiceImpl implements TicketService,TicketServiceForBl {

    @Autowired
    ConsumeService consumeService;
    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    ScheduleServiceForBl scheduleService;
    @Autowired
    HallServiceForBl hallService;
    @Autowired
    CouponServiceForBl couponService;
    @Autowired
    ActivityServiceForBl activityService;
    @Autowired
    VIPServiceForBl vipService;
    @Autowired
    RefundServiceForBl refundService;
    @Autowired
    ScheduleMapper scheduleMapper;
    @Autowired
    ConsumeServiceForBI consumeServiceForBI;
    @Autowired
    CouponMapper couponMapper;

    @Override
    @Transactional
    public ResponseVO addTicket(TicketForm ticketForm) {
        // 首先将该用户已选座位但未支付的电影票移除
        ticketMapper.deleteLockedTicket(ticketForm.getUserId(),ticketForm.getScheduleId());

        List<Ticket> tickets = new ArrayList<>();
        List<SeatForm> seats = ticketForm.getSeats();
        for (SeatForm seat: seats){
            Ticket ticket = new Ticket();
            ticket.setUserId(ticketForm.getUserId());
            ticket.setScheduleId(ticketForm.getScheduleId());
            ticket.setRowIndex(seat.getRowIndex());
            ticket.setColumnIndex(seat.getColumnIndex());
            ticket.setState(0);
            tickets.add(ticket);
        }
        try {
            ticketMapper.insertTickets(tickets);
            return getOtherInfo(ticketForm);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    @Transactional
    public ResponseVO completeTicket(List<Integer> id, int couponId) {
        //   1. 默认成功(√)
        //   2. 校验优惠券是否存在、是否能用(√)
        //   3. 根据活动赠送优惠券(√)

        if (id==null || id.size()==0){
            return ResponseVO.buildFailure("票不存在");
        }

        Ticket ticket=ticketMapper.selectTicketById(id.get(0));
        ScheduleItem scheduleItem=scheduleService.getScheduleItemById(ticket.getScheduleId());
        double totalPay=scheduleItem.getFare()*id.size();
        if(! isCouponEnable(couponId,totalPay,ticket.getUserId())){
            return ResponseVO.buildFailure("优惠券不能使用");
        }else{
            if(couponId!=0){
                totalPay=totalPay-((Coupon)couponService.getCoupon(couponId).getContent()).getDiscountAmount();
                couponService.deleteCoupon(couponId,ticket.getUserId());
            }
            for (int i:id){
                ticketMapper.updateTicketState(i,1);
                ticketMapper.updateTicketActualPay(i,totalPay/id.size());
            }
        }

        //添加消费记录
        Coupon coupon = (Coupon)couponService.getCoupon(couponId).getContent();
        if (null == coupon) {
            consumeService.addConsumeHistory(ticket.getUserId(), totalPay, 0.0,
                    "会员卡", ConsumeHistory.BUY_TICKET, ticket.getId());
        } else {
            consumeService.addConsumeHistory(ticket.getUserId(), totalPay, coupon.getDiscountAmount(),
                    "会员卡", ConsumeHistory.BUY_TICKET, ticket.getId());
        }

        //将电影票与消费记录关联
        int consumeId=consumeServiceForBI.getConsumeByTicketId(ticket.getId());
        for(int ticketId:id){
            ticketMapper.insertTicketAndConsume(ticketId, consumeId);
        }

        //赠送优惠券
        List<Activity> list= (List<Activity>) activityService.getActivitiesByMovie(scheduleItem.getMovieId()).getContent();
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        for (Activity temp:list){
            if (timestamp.after(temp.getStartTime()) && timestamp.before(temp.getEndTime()) && totalPay>=temp.getTargetAmount()){
                couponService.issueCoupon(temp.getCoupon().getId(),ticket.getUserId());
            }
        }

        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO getBySchedule(int scheduleId) {
        try {
            // 此时已经将失效（state==2）的电影票过滤掉了
            List<Ticket> tickets = ticketMapper.selectTicketsBySchedule(scheduleId);
            ScheduleItem schedule = scheduleService.getScheduleItemById(scheduleId);
            Hall hall = hallService.getHallById(schedule.getHallId());
            int[][] seats = hall.getParsedSeats();
            // 当前用户已选但未支付的座位为2，否则为1
            tickets.forEach(ticket -> {
                if (ticket.getState() == 0)
                    seats[ticket.getRowIndex()][ticket.getColumnIndex()] = 2;
                else
                    seats[ticket.getRowIndex()][ticket.getColumnIndex()] = 1;
            });
            ScheduleWithSeatVO scheduleWithSeatVO=new ScheduleWithSeatVO();
            scheduleWithSeatVO.setScheduleItem(schedule);
            scheduleWithSeatVO.setSeats(seats);
            return ResponseVO.buildSuccess(scheduleWithSeatVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getTicketByUser(int userId) {
        try {
            List<Ticket> ticketList = ticketMapper.selectTicketByUser(userId);
            List<UserTicketVO> res = new ArrayList<>();
            for (Ticket ticket : ticketList) {
                ScheduleItem scheduleItem = scheduleService.getScheduleItemById(ticket.getScheduleId());
                UserTicketVO vo = new UserTicketVO();
                vo.setId(ticket.getId());
                vo.setColumnIndex(ticket.getColumnIndex());
                vo.setRowIndex(ticket.getRowIndex());
                vo.setScheduleId(ticket.getScheduleId());
                vo.setUserId(ticket.getUserId());
                vo.setTime(ticket.getTime());
                vo.setMovieName(scheduleItem.getMovieName());
                vo.setHallName(scheduleItem.getHallName());
                vo.setStartTime(scheduleItem.getStartTime());
                vo.setEndTime(scheduleItem.getEndTime());
                vo.setState(ticket.getState());
                vo.setMovieId(scheduleItem.getMovieId());
                res.add(vo);
            }
            return ResponseVO.buildSuccess(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("获取购票历史失败!");
        }
    }

    @Override
    @Transactional
    public ResponseVO completeByVIPCard(List<Integer> id, int couponId) {
        //   1. 调用VIPService的方法更新会员卡余额(√)
        //   2. 校验优惠券是否存在、是否能用(√)
        //   3. 用boolean ResponseVO.success表示支付是否成功(√)
        //   4. 根据活动赠送优惠券(√)

        if (id.size()==0 || id==null){
            return ResponseVO.buildFailure("票不存在");
        }

        Ticket ticket=ticketMapper.selectTicketById(id.get(0));
        ScheduleItem scheduleItem=scheduleService.getScheduleItemById(ticket.getScheduleId());
        double totalPay=scheduleItem.getFare()*id.size();
        if(! isCouponEnable(couponId,totalPay,ticket.getUserId())){
            return ResponseVO.buildFailure("优惠券不能使用");
        }else{
            if(couponId!=0){
                totalPay=totalPay-((Coupon)couponService.getCoupon(couponId).getContent()).getDiscountAmount();
            }
            //更新会员卡余额
            if (! vipService.getCardByUserId(ticket.getUserId()).getSuccess()){
                return ResponseVO.buildFailure("会员卡获取失败");
            }else{
                VIPCard vipCard= (VIPCard) vipService.getCardByUserId(ticket.getUserId()).getContent();
                if (vipCard.getBalance()<totalPay){
                    return ResponseVO.buildFailure("会员卡余额不足");
                }
                vipService.payByCard(vipCard.getId(),vipCard.getBalance()-totalPay);
                couponService.deleteCoupon(couponId,ticket.getUserId());
            }
            for (int i:id){
                ticketMapper.updateTicketState(i,1);
                ticketMapper.updateTicketActualPay(i,totalPay/id.size());
            }
        }

        //添加消费记录
        Coupon coupon = (Coupon)couponService.getCoupon(couponId).getContent();
        if (null == coupon) {
            consumeService.addConsumeHistory(ticket.getUserId(), totalPay, 0.0,
                    "会员卡", ConsumeHistory.BUY_TICKET, ticket.getId());
        } else {
            consumeService.addConsumeHistory(ticket.getUserId(), totalPay, coupon.getDiscountAmount(),
                    "会员卡", ConsumeHistory.BUY_TICKET, ticket.getId());
        }

        //将电影票与消费记录关联
        int consumeId=consumeServiceForBI.getConsumeByTicketId(ticket.getId());
        for(int ticketId:id){
            ticketMapper.insertTicketAndConsume(ticketId, consumeId);
        }

        //赠送优惠券
        List<Activity> list= (List<Activity>) activityService.getActivitiesByMovie(scheduleItem.getMovieId()).getContent();
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        for (Activity temp:list){
            if (timestamp.after(temp.getStartTime()) && timestamp.before(temp.getEndTime()) && totalPay>=temp.getTargetAmount()){
                couponService.issueCoupon(temp.getCoupon().getId(),ticket.getUserId());
            }
        }

        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO cancelTicket(List<Integer> id) {
        try {
            for (int i = 0; i < id.size(); i++) {
                Ticket ticket=ticketMapper.selectTicketById(id.get(i));
                if (ticket.getState() == 0 || ticket.getState() == 2 || ticket.getState() == 3 || ticket.getState() == 4) {
                    ticketMapper.deleteTicket(ticket.getId());
                }else if (ticket.getState() == 1) {
                    if (vipService.getCardByUserId(ticket.getUserId()).getSuccess()){
                        VIPCard vipCard= (VIPCard) vipService.getCardByUserId(ticket.getUserId()).getContent();
                        double refundPrice=ticket.getActualPay()*getRefundStrategy(ticket.getScheduleId());  //应退还金额
                        vipCard.setBalance(vipCard.getBalance()+refundPrice);
                        vipService.payByCard(vipCard.getId(),vipCard.getBalance());
                    }
                    ticketMapper.updateTicketState(ticket.getId(),4);
                }
            }
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            return ResponseVO.buildFailure("取消锁座失败!");
        }
    }

    @Override
    public ResponseVO issueTicket(int id) {
        try{
            ticketMapper.updateTicketState(id,3);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            return ResponseVO.buildFailure("出票失败!");
        }
    }

    @Override
    public ResponseVO getTicketRefund(int id) {
        try{
            Ticket ticket=ticketMapper.selectTicketById(id);
            double discount=getRefundStrategy(ticket.getScheduleId())*100;
            return ResponseVO.buildSuccess(discount);
        }catch (Exception e) {
            return ResponseVO.buildFailure("获取退票折算策略失败!");
        }
    }

    private ResponseVO getOtherInfo(TicketForm ticketForm) {
        try {
            TicketWithCouponVO ticketWithCouponVO = new TicketWithCouponVO();
            List<TicketVO> ticketVOList = new ArrayList<>();
            for (SeatForm seat: ticketForm.getSeats()){
                ticketVOList.add(ticketMapper.selectTicketByScheduleIdAndSeat(ticketForm.getScheduleId(), seat.getColumnIndex(), seat.getRowIndex()).getVO());
            }
            ticketWithCouponVO.setTicketVOList(ticketVOList);
            ticketWithCouponVO.setTotal(ticketVOList.size() * scheduleService.getScheduleItemById(ticketForm.getScheduleId()).getFare());
            List<Coupon> coupons = new ArrayList<>();
            for (Coupon temp:(List<Coupon>) couponService.getCouponsByUser(ticketForm.getUserId()).getContent()){
                if (temp.getTargetAmount() < ticketWithCouponVO.getTotal()){
                    coupons.add(temp);
                }
            }
            ticketWithCouponVO.setCoupons(coupons);
            ticketWithCouponVO.setActivities(activityService.getActivityList());
            return ResponseVO.buildSuccess(ticketWithCouponVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    public ResponseVO getUserTicketToPay(int userId, int scheduleId) {
        try {
            PayInfo payInfo = new PayInfo();

            List<TicketToPayVO> ticketList = new ArrayList<>();
            double totalFare = 0;
            for (Ticket ticket : ticketMapper.selectTicketsByUserAndSchedule(userId, scheduleId)) {
                // 只有未完成的票可以进行支付
                if (ticket.getState() == 0) {
                    TicketToPayVO ticketToPayVO = new TicketToPayVO();
                    ticketToPayVO.setTicketId(ticket.getId());
                    ticketToPayVO.setRowIndex(ticket.getRowIndex());
                    ticketToPayVO.setColumnIndex(ticket.getColumnIndex());
                    ticketToPayVO.setSchedule(scheduleMapper.selectScheduleById(ticket.getScheduleId()));
                    ticketList.add(ticketToPayVO);

                    totalFare += ticketToPayVO.getSchedule().getFare();
                }
            }


            List<Coupon> coupons = new ArrayList<>();
            for (Coupon coupon : couponMapper.selectCouponByUser(userId)) {
                if (coupon.getTargetAmount() <= totalFare) {
                    coupons.add(coupon);
                }
            }

            payInfo.setTicketList(ticketList);
            payInfo.setCoupons(coupons);
            return ResponseVO.buildSuccess(payInfo);
        } catch (Exception e) {
            return ResponseVO.buildFailure("获取待支付信息失败");
        }
    }


    //检验优惠券是否存在，是否能用(门槛，时间)，返回应付总金额
    private boolean isCouponEnable(int couponId, double totalPay, int userId){
        //检验是否使用优惠券
        if(couponId==0){
            return true;
        }

        Coupon coupon=null;
        //检验是否存在
        List<Coupon> couponList= (List<Coupon>) couponService.getCouponsByUser(userId).getContent();
        boolean isOk=false;
        for (Coupon temp:couponList){
            if (temp.getId()==couponId){
                isOk=true;
                coupon=temp;
                break;
            }
        }
        if (!isOk){
            return false;
        }

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        if (!(timestamp.after(coupon.getStartTime()) && timestamp.before(coupon.getEndTime()))){
            //检验时间
            return false;
        }
        if(coupon.getTargetAmount()>totalPay){
            //检验门槛金额
            return false;
        }
        return true;
    }

    //通过id获得电影票详细信息
    public Ticket getTicketById(int ticketId){
        return ticketMapper.selectTicketById(ticketId);
    }

    @Override
    public int getTicketNumBySchedule(int scheduleId) {
        return ticketMapper.selectTicketNumBySchedule(scheduleId);
    }

    //获取应该使用的退票策略，直接返回折算策略
    private double getRefundStrategy(int scheduleId){
        ScheduleItem scheduleItem=scheduleService.getScheduleItemById(scheduleId);
        List<Refund> refundList=refundService.getRefundByMovieId(scheduleItem.getMovieId());
        Date date = new Date();
        int time= (int) ((date.getTime()-scheduleItem.getStartTime().getTime())/(1000*60*60*24));
        double minTime=10000,discount=100;
        for(Refund temp:refundList){
            if (temp.getTime()>time){
                if (temp.getTime()<minTime){
                    minTime=temp.getTime();
                    discount=temp.getPrice();
                }
            }
        }
        return discount/100.0;
    }

}
