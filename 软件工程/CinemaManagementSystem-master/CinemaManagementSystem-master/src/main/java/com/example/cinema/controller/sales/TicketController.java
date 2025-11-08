package com.example.cinema.controller.sales;

import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.TicketAndCouponVO;
import com.example.cinema.vo.TicketForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping("/vip/buy")
    public ResponseVO buyTicketByVIPCard(@RequestBody TicketAndCouponVO ticketAndCouponVO){
        return ticketService.completeByVIPCard(ticketAndCouponVO.getTicketId(), ticketAndCouponVO.getCouponId());
    }

    @PostMapping("/lockSeat")
    public ResponseVO lockSeat(@RequestBody TicketForm ticketForm){
        return ticketService.addTicket(ticketForm);
    }

    @PostMapping("/buy")
    public ResponseVO buyTicket(@RequestBody TicketAndCouponVO ticketAndCouponVO){
        return ticketService.completeTicket(ticketAndCouponVO.getTicketId(), ticketAndCouponVO.getCouponId());
    }
    @GetMapping("/get/{userId}")
    public ResponseVO getTicketByUserId(@PathVariable int userId){
        return ticketService.getTicketByUser(userId);
    }

    @GetMapping("/get/occupiedSeats")
    public ResponseVO getOccupiedSeats(@RequestParam int scheduleId){
        return ticketService.getBySchedule(scheduleId);
    }
    @PostMapping("/cancel")
    public ResponseVO cancelTicket(@RequestParam List<Integer> ticketId){
        return ticketService.cancelTicket(ticketId);
    }

    @GetMapping("/delete")
    public ResponseVO deleteTicket(@RequestParam int ticketId){
        List<Integer> list=new ArrayList<Integer>();
        list.add(ticketId);
        return ticketService.cancelTicket(list);
    }

    @GetMapping("/issue")
    public ResponseVO issueTicket(@RequestParam int ticketId){
        return ticketService.issueTicket(ticketId);
    }

    @GetMapping("/getTicketRefund")
    public ResponseVO getTicketRefund(@RequestParam int ticketId){
        return ticketService.getTicketRefund(ticketId);
    }

    @GetMapping("/toPay")
    public ResponseVO getUserTicketToPay(@RequestParam int userId, @RequestParam int scheduleId) {
        return ticketService.getUserTicketToPay(userId, scheduleId);
    }

}
