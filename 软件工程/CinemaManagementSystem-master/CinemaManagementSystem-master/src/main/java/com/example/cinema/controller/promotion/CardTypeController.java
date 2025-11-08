package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.CardTypeService;
import com.example.cinema.vo.CardTypeForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @date 2019-5-20
 * @author zhm
 */
@RestController
@RequestMapping("/card")
public class CardTypeController {

    @Autowired
    CardTypeService cardTypeService;

    @GetMapping("/get")
    public ResponseVO getCards(){
        return cardTypeService.getCards();
    }

    @PostMapping("/publish")
    public ResponseVO publishCard(@RequestBody CardTypeForm cardTypeForm){
        return cardTypeService.publishCard(cardTypeForm);
    }

    @GetMapping("/delete/{cardId}")
    public ResponseVO deleteCard(@PathVariable int cardId){
        return cardTypeService.deleteCard(cardId);
    }

    @PostMapping("/update/{cardId}")
    public ResponseVO updateCard(@PathVariable int cardId, @RequestBody CardTypeForm cardTypeForm){
        return cardTypeService.updateCard(cardId, cardTypeForm);
    }
}
