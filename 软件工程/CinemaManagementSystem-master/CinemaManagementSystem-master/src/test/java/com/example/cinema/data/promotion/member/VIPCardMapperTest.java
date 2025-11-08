package com.example.cinema.data.promotion.member;

import com.example.cinema.CinemaApplication;
import com.example.cinema.data.promotion.VIPCardMapper;
import com.example.cinema.po.VIPCard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = CinemaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class VIPCardMapperTest {

    @Autowired
    VIPCardMapper vipCardMapper;

    @Test
    public void selectCardByIdTest() {
        VIPCard vipCard = vipCardMapper.selectCardById(1);
        Assert.assertEquals(1,vipCard.getId());
    }

    @Test
    public void updateCardBalanceTest() {
        vipCardMapper.updateCardBalance(1,200);
        VIPCard vipCard = vipCardMapper.selectCardById(1);
        Assert.assertEquals(200,(int)vipCard.getBalance());
    }
}
