package com.example.cinema.controller.management;

import com.example.cinema.bl.management.HallService;
import com.example.cinema.vo.HallVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 影厅管理
 *
 * @author fjj, chph
 * @date 2019/4/12 1:59 PM
 */
@RestController
@RequestMapping("/hall")
public class HallController {
    @Autowired
    private HallService hallService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseVO searchAllHall() {
        return hallService.searchAllHall();
    }

    @PostMapping("/add")
    public ResponseVO addHall(@RequestBody HallVO hallVO) {
        return hallService.addHall(hallVO);
    }

    @PostMapping("/update")
    public ResponseVO updateHall(@RequestBody HallVO hallVO) {
        return hallService.updateHall(hallVO);
    }

    @RequestMapping("/remove")
    public ResponseVO removeHall(int hallId) {
        return hallService.removeHall(hallId);
    }

    @RequestMapping("/available")
    public ResponseVO getAvailableHalls(){
        return hallService.getAvailableHalls();
    }
}
