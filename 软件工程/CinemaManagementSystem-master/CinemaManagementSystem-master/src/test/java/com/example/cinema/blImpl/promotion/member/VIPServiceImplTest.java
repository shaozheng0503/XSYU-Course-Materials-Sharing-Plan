package com.example.cinema.blImpl.promotion.member;

import com.example.cinema.CinemaApplication;
import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.po.VIPCard;
import com.example.cinema.vo.VIPCardForm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = CinemaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class VIPServiceImplTest {

    @Autowired
    VIPService vipService;

    @Test
    public void getCardByUserIdTestA() {
        VIPCard vipCard = (VIPCard) vipService.getCardByUserId(12).getContent();
        Assert.assertEquals(12,vipCard.getUserId());
    }

    @Test
    public void getCardByUserIdTestB() {
        String message = vipService.getCardByUserId(2).getMessage();
        Assert.assertEquals("用户卡不存在",message);
    }

    @Test
    public void addVIPCardTest() {
        vipService.addVIPCard(1,2);
        VIPCard vipCard = (VIPCard) vipService.getCardByUserId(1).getContent();
        Assert.assertEquals(1,vipCard.getUserId());
        Assert.assertEquals(2,vipCard.getCardTypeId());
        Assert.assertEquals(0,(int)vipCard.getBalance());
    }

    @Test
    public void chargeTest() {
        VIPCard oldVIP = (VIPCard) vipService.getCardById(1).getContent();
        VIPCardForm vipCardForm = new VIPCardForm();
        vipCardForm.setVipId(1);
        vipCardForm.setAmount(200);
        vipService.charge(vipCardForm);
        VIPCard newVIP = (VIPCard) vipService.getCardById(1).getContent();
        int target = (int)oldVIP.calculateTopUpDiscount(200);
        int real = (int)(newVIP.getBalance()-oldVIP.getBalance());
        Assert.assertEquals(target,real);
    }

    @Test
    public void changeVIPCardTest() {
        VIPCard oldVIP = (VIPCard) vipService.getCardByUserId(12).getContent();
        vipService.changeVIPCard(oldVIP.getId(),2);
        VIPCard newVIP = (VIPCard) vipService.getCardByUserId(12).getContent();
        Assert.assertEquals(2, newVIP.getCardTypeId());
    }
}
