package com.example.cinema.data.promotion.member;

import com.example.cinema.CinemaApplication;
import com.example.cinema.data.promotion.CardTypeMapper;
import com.example.cinema.po.CardType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest(classes = CinemaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CardTypeMapperTest {

    @Autowired
    private CardTypeMapper cardTypeMapper;

    @Test
    public void selectCardByIdTest() {
        CardType cardType = cardTypeMapper.selectCardById(2);
        Assert.assertEquals(2, cardType.getId());
    }

    @Test
    public void selectAllCardsTest() {
        List<CardType> cardTypes = cardTypeMapper.selectAllCards();
        Assert.assertEquals(1, cardTypes.size());
    }

    @Test
    public void deleteCardByIdTest() {
        Assert.assertEquals(1,cardTypeMapper.deleteCardById(1));
    }

    @Test
    public void updateCardByIdTest() {
        CardType cardType = cardTypeMapper.selectCardById(1);
        cardType.setPrice(50);
        cardTypeMapper.updateCardById(1,cardType);
        CardType cardTypeU = cardTypeMapper.selectCardById(1);
        Assert.assertEquals(50,(int)cardTypeU.getPrice());
    }
}
