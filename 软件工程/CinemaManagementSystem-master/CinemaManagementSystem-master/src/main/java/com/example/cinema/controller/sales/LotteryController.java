package com.example.cinema.controller.sales;

import com.example.cinema.bl.sales.LotteryService;
import com.example.cinema.config.InterceptorConfiguration;
import com.example.cinema.po.User;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LotteryController {

    @Autowired
    LotteryService lotteryService;

    @GetMapping("/lottery/get")
    public ResponseVO getLottery(@RequestParam("min") Double min, @RequestParam("max") Double max,
                                 @RequestParam("num") int num, HttpSession session) {
        return lotteryService.getLottery(min, max, num, ((User)session.getAttribute(InterceptorConfiguration.SESSION_KEY)).getId());
    }
}
