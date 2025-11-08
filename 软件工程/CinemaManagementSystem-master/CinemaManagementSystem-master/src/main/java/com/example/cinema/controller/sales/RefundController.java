package com.example.cinema.controller.sales;

import com.example.cinema.bl.sales.RefundService;
import com.example.cinema.vo.RefundForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @date 2019-5-18
 * @author CZ
 */

@RestController
@RequestMapping("/refund")
public class RefundController {

    @Autowired
    RefundService refundService;

    @GetMapping("/all")
    public ResponseVO getAllRefund(){
        return refundService.getAllRefund();
    }

    @PostMapping("/add")
    public ResponseVO publishRefund(@RequestBody RefundForm refundForm){
        return refundService.publishRefund(refundForm);
    }

    @GetMapping("/delete")
    public ResponseVO deleteRefund(@RequestParam int refundId){
        return refundService.deleteRefund(refundId);
    }

    @GetMapping("/get")
    public ResponseVO getRefundById(@RequestParam int refundId){
        return refundService.getRefundById(refundId);
    }

}
