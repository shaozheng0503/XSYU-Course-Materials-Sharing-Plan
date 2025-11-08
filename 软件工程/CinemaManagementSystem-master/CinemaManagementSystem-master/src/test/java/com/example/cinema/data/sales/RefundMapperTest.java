package com.example.cinema.data.sales;

import com.example.cinema.CinemaApplication;
import com.example.cinema.po.Refund;
import com.example.cinema.po.Ticket;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest(classes= CinemaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RefundMapperTest {

    @Autowired
    RefundMapper refundMapper;
    private static int id;

    @Test
    public void AinsertRefundTest(){
        Refund refund=new Refund();
        refund.setTime(1);
        refund.setMovieId(0);
        refund.setPrice(1);
        int res=refundMapper.insertRefund(refund);
        Assert.assertEquals(1,res);
    }

    @Test
    public void BselectRefundTest(){
        List<Refund> refunds=refundMapper.selectRefund();
        Assert.assertEquals(3,refunds.size());
    }

    @Test
    public void CselectRefundByMovieIdTest(){
        List<Refund> refunds=refundMapper.selectRefundByMovieId(0);
        id=refunds.get(0).getId();
        Assert.assertEquals(1,refunds.size());
    }

    @Test
    public void DselectRefundByIdTest(){
        Refund refund=refundMapper.selectRefundById(id);
        Assert.assertEquals(id,refund.getId());
    }

    @Test
    public void EdeleteRefundByIdTest(){
        int res=refundMapper.deleteRefundById(id);
        Assert.assertEquals(1,res);
    }

}
