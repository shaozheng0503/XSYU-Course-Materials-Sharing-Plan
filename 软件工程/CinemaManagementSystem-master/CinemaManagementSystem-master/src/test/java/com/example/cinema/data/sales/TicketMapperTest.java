package com.example.cinema.data.sales;

import com.example.cinema.CinemaApplication;
import com.example.cinema.po.Ticket;
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

@SpringBootTest(classes=CinemaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TicketMapperTest {

    @Autowired
    TicketMapper ticketMapper;
    private static int id1;

    @Test
    public void AinsertTicketTest(){
        Ticket ticket=new Ticket();
        ticket.setUserId(0);
        ticket.setScheduleId(0);
        ticket.setColumnIndex(0);
        ticket.setRowIndex(0);
        ticket.setState(0);
        int res=ticketMapper.insertTicket(ticket);
        Assert.assertEquals(1,res);
    }

    @Test
    public void BinsertTicketsTest(){
        List<Ticket> tickets=new ArrayList<>();
        Ticket ticket=new Ticket();
        ticket.setUserId(0);
        ticket.setScheduleId(0);
        ticket.setColumnIndex(1);
        ticket.setRowIndex(1);
        ticket.setState(0);
        tickets.add(ticket);
        int res=ticketMapper.insertTickets(tickets);
        Assert.assertEquals(1,res);
    }

    @Test
    public void CselectTicketsByScheduleTest(){
        List<Ticket> tickets=ticketMapper.selectTicketsBySchedule(0);
        Assert.assertEquals(2,tickets.size());
    }

    @Test
    public void DselectTicketByScheduleIdAndSeatTest(){
        Ticket ticket=ticketMapper.selectTicketByScheduleIdAndSeat(0,0,0);
        id1=ticket.getId();
        Assert.assertEquals(0,ticket.getUserId());
    }

    @Test
    public void EselectTicketByIdTest(){
        Ticket ticket=ticketMapper.selectTicketById(id1);
        Assert.assertEquals(id1,ticket.getId());
    }

    @Test
    public void FselectTicketByUser(){
        List<Ticket> tickets=ticketMapper.selectTicketByUser(0);
        Assert.assertEquals(2,tickets.size());
    }

    @Test
    public void GupdateTicketStateTest(){
        ticketMapper.updateTicketState(id1,1);
        Ticket ticket=ticketMapper.selectTicketById(id1);
        Assert.assertEquals(1,ticket.getState());
    }

    @Test
    public void HupdateTicketActualPay(){
        ticketMapper.updateTicketActualPay(id1,101);
        Ticket ticket=ticketMapper.selectTicketById(id1);
        Assert.assertEquals(101,ticket.getActualPay(),0.000001);
    }

    @Test
    public void IdeleteTicketTest(){
        int res=ticketMapper.deleteTicket(id1);
        Assert.assertEquals(1,res);
    }

    @Test
    public void JdeleteLockedTicketTest(){
        int res=ticketMapper.deleteLockedTicket(0,0);
        Assert.assertEquals(1,res);
    }

}
