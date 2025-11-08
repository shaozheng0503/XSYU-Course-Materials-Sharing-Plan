package com.example.cinema.data.consume;

import com.example.cinema.CinemaApplication;
import com.example.cinema.data.consume.ConsumeMapper;
import com.example.cinema.po.ConsumeHistory;
import com.example.cinema.po.TopUpHistory;
import com.example.cinema.vo.TotalConsumeUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest(classes = CinemaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ConsumeMapperTest {

    @Autowired
    private ConsumeMapper consumeMapper;

    @Test
    public void getTopUpHistoryByUserIdTest() {
        List<TopUpHistory> histories = consumeMapper.getTopUpHistoryByUserId(12);
        Assert.assertEquals(3,histories.size());
    }

    @Test
    public void insertTopUpHistoryTest() {
        Assert.assertEquals(consumeMapper.insertTopUpHistory(15,100.0,20.0,500.0, new Timestamp(System.currentTimeMillis()))
                    ,1);
    }

    @Test
    public void insertConsumeHistoryTest() {
        Assert.assertEquals(consumeMapper.insertConsumeHistory(15,100.0,20.0,"银行卡",ConsumeHistory.BUY_VIP_CARD,1)
                    ,1);
    }

    @Test
    public void getConsumeQualifiedUsersTest() {
        List<TotalConsumeUser> list = consumeMapper.selectConsumeQulifiedUsers(500.0);
        Assert.assertEquals(list.size(),2);
        Assert.assertEquals(list.get(0).getUserId(),12);
    }
}
