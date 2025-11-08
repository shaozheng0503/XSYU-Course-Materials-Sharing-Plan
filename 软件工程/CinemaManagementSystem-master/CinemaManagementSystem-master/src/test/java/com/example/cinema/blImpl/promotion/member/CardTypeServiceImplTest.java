package com.example.cinema.blImpl.promotion.member;

import com.example.cinema.CinemaApplication;
import com.example.cinema.bl.promotion.CardTypeService;
import com.example.cinema.po.CardType;
import com.example.cinema.vo.CardTypeForm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@SpringBootTest(classes = CinemaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CardTypeServiceImplTest {

    @Autowired
    CardTypeService cardTypeService;

    @Test
    public void publishCardTest() {
        CardTypeForm cardTypeForm = new CardTypeForm();
        cardTypeForm.setName("Agent67");
        cardTypeForm.setPrice(50);
        cardTypeForm.setTopUpTarget(100);
        cardTypeForm.setTopUpDiscount(20);
        cardTypeService.publishCard(cardTypeForm);
        List<CardType> cardTypes = (List<CardType>)cardTypeService.getCards().getContent();
        CardType cardType = cardTypes.get(cardTypes.size()-1);
        Assert.assertEquals("充值会员卡满100.0送20.0",cardType.getDescription());
    }
}
