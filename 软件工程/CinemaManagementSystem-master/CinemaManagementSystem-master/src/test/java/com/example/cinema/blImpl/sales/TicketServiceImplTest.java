package com.example.cinema.blImpl.sales;

import com.example.cinema.CinemaApplication;
import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.SeatForm;
import com.example.cinema.vo.TicketForm;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = CinemaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TicketServiceImplTest {

    @Autowired
    TicketService ticketService;

    @Test
    public void addTicketTest(){
        TicketForm ticketForm=new TicketForm();
        SeatForm seatForm=new SeatForm();
        List<SeatForm> seatForms=new ArrayList<>();
        seatForm.setColumnIndex(0);
        seatForm.setRowIndex(0);
        ticketForm.setUserId(0);
        ticketForm.setScheduleId(69);
        seatForms.add(seatForm);
        ticketForm.setSeats(seatForms);
        ResponseVO responseVO=ticketService.addTicket(ticketForm);
        Assert.assertEquals(true,responseVO.getSuccess());
    }

    @Test
    public void completeTicketTest(){
        List<Integer> integers=new ArrayList<>();
        integers.add(1);
        ResponseVO responseVO=ticketService.completeTicket(integers,0);
        Assert.assertEquals(true,responseVO.getSuccess());
    }

    @Test
    public void getByScheduleTest(){
        ResponseVO responseVO=ticketService.getBySchedule(69);
        Assert.assertEquals(true,responseVO.getSuccess());
    }
}
