package com.example.cinema.controller.consume;

import com.example.cinema.bl.consume.ConsumeService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hxw
 * @date 2019-5-21
 */
@RestController
public class ConsumeController {

    @Autowired
    private ConsumeService consumeService;

    @GetMapping("/history/topup/get")
    ResponseVO getAllTopupHistory(@RequestParam("userId") Integer userId) {
        return consumeService.getAllTopUpHistory(userId);
    }

    @GetMapping("/history/consume/get")
    ResponseVO getAllConsumeHistory(@RequestParam("userId") Integer userId) {
        return consumeService.getBriefConsumeHis(userId);
    }

    @GetMapping("/history/consume/get/detail")
    ResponseVO getConsumeHisDetail(@RequestParam("id") Integer id) {
        return consumeService.getConsumeHisDetail(id);
    }

}
