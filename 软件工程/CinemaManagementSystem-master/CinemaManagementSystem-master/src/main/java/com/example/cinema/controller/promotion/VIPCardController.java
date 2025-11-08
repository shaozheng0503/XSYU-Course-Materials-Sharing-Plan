package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liying on 2019/4/14.
 */
@RestController()
@RequestMapping("/vip")
public class VIPCardController {
    @Autowired
    VIPService vipService;

    @PostMapping("/add/{userId}/{cardTypeId}")
    public ResponseVO addVIP(@PathVariable int userId, @PathVariable int cardTypeId){
        return vipService.addVIPCard(userId, cardTypeId);
    }

    @GetMapping("/get/{userId}")
    public ResponseVO getVIP(@PathVariable int userId){
        return vipService.getCardByUserId(userId);
    }

    @PostMapping("/charge")
    public ResponseVO charge(@RequestBody VIPCardForm vipCardForm){
        return vipService.charge(vipCardForm);
    }

    @PostMapping("/change/{cardId}/{cardTypeId}")
    public ResponseVO changeVIP(@PathVariable int cardId, @PathVariable int cardTypeId){
        return vipService.changeVIPCard(cardId, cardTypeId);
    }


}
