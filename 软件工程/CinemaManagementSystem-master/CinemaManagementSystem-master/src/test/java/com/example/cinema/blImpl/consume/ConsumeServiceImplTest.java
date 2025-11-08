package com.example.cinema.blImpl.consume;

import com.example.cinema.CinemaApplication;
import com.example.cinema.bl.consume.ConsumeService;
import com.example.cinema.vo.BriefConsumeHisVO;
import com.example.cinema.vo.BuyCardHistoryVO;
import com.example.cinema.vo.BuyTicketHistoryVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest(classes = CinemaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ConsumeServiceImplTest {

    private final static Logger logger = LoggerFactory.getLogger("****ConsumeServiceImplTest****");

    @Autowired
    private ConsumeService consumeService;

    @Test
    public void getBriefConsumeHisTest() {
        List<BriefConsumeHisVO> content = (List<BriefConsumeHisVO>) consumeService.getBriefConsumeHis(15).getContent();
        Assert.assertEquals(4,content.size());
        BriefConsumeHisVO item = content.get(0);
        Assert.assertEquals(20.5,item.getMoney(),0.00);
        Assert.assertEquals("购买电影票",item.getType());
    }

    @Test
    public void getConsumeHisDetailTest1() {
        BuyTicketHistoryVO history = (BuyTicketHistoryVO) consumeService.getConsumeHisDetail(2).getContent();
        Assert.assertEquals(30,history.getMoney(),0.00);
        Assert.assertEquals("夏目友人帐",history.getMovieName());
        Assert.assertEquals("1号厅",history.getHallName());
    }

    @Test
    public void getConsumeHisDetailTest2() {
        BuyCardHistoryVO history = (BuyCardHistoryVO) consumeService.getConsumeHisDetail(4).getContent();
        Assert.assertEquals(25,history.getMoney(),0.00);
        Assert.assertEquals("白金VIP",history.getCardType());
    }
}
