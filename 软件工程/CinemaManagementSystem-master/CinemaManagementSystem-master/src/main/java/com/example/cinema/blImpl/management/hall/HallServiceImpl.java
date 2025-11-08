package com.example.cinema.blImpl.management.hall;

import com.example.cinema.bl.management.HallService;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.data.management.HallMapper;
import com.example.cinema.po.Hall;
import com.example.cinema.vo.HallVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fjj, chph
 * @date 2019/4/12 2:01 PM
 */
@Service
public class HallServiceImpl implements HallService, HallServiceForBl {
    @Autowired
    private HallMapper hallMapper;

    @Autowired
    private ScheduleServiceForBl scheduleServiceForBl;

    @Override
    public ResponseVO searchAllHall() {
        try {
            return ResponseVO.buildSuccess(hallList2HallVOList(hallMapper.selectAllHall()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO addHall(HallVO hallVO) {
        int[][] seats = hallVO.getSeats();
        int seatCount = seats.length * seats[0].length;
        for (int[] seat : seats) {
            for (int j = 0; j < seats[0].length; j++) {
                seatCount += seat[j];
            }
        }
        if (seatCount > 500)
            return ResponseVO.buildFailure("影厅座位数不能超过500，请修改！");

        if (hallMapper.checkHallName(hallVO.getName(), 0) != 0)
            return ResponseVO.buildFailure("已经存在名称为\"" + hallVO.getName() + "\"的影厅，请修改！");

        Hall hall = new Hall();
        hall.setName(hallVO.getName());
        hall.setScale(hallVO.getScale());
        hall.setGeneratedSeats(hallVO.getSeats());
        // result是返回插入结果的影响条数，为1时说明插入成功
        int result = hallMapper.addHall(hall);
        if (result == 1) return ResponseVO.buildSuccess();
        else return ResponseVO.buildFailure("录入影厅信息失败！");
    }

    @Override
    public ResponseVO updateHall(HallVO hallVO) {
        int[][] seats = hallVO.getSeats();
        int seatCount = seats.length * seats[0].length;
        for (int[] seat : seats) {
            for (int j = 0; j < seats[0].length; j++) {
                seatCount += seat[j];
            }
        }
        if (seatCount > 500)
            return ResponseVO.buildFailure("影厅座位数不能超过500，请修改！");

        if (hallMapper.checkHallName(hallVO.getName(), hallVO.getId()) != 0)
            return ResponseVO.buildFailure("已经存在名称为\"" + hallVO.getName() + "\"的影厅，请修改！");

        Hall hall = new Hall();
        hall.setId(hallVO.getId());
        hall.setName(hallVO.getName());
        hall.setScale(hallVO.getScale());
        hall.setGeneratedSeats(hallVO.getSeats());
        // result是返回更新结果的影响条数，为1时说明更新成功
        int result = hallMapper.updateHall(hall);
        if (result == 1) return ResponseVO.buildSuccess();
        else return ResponseVO.buildFailure("更新影厅信息失败！");
    }

    @Override
    public ResponseVO removeHall(int hallId) {
        // result是返回删除结果的影响条数，为1时说明删除成功
        int result = hallMapper.removeHallById(hallId);
        if (result == 1) return ResponseVO.buildSuccess();
        else return ResponseVO.buildFailure("删除影厅失败！");
    }

    @Override
    public ResponseVO getAvailableHalls() {
        List<Integer> hallsInUse = scheduleServiceForBl.getScheduledHalls();
        List<Hall> availableHalls = hallMapper.getHallsExcept(hallsInUse);
        if (availableHalls != null) {
            List<HallVO> result = availableHalls.stream().map(hall -> {
                HallVO hallVO = new HallVO();
                hallVO.setId(hall.getId());
                hallVO.setName(hall.getName());
                hallVO.setScale(hall.getScale());
                hallVO.setSeats(hall.getParsedSeats());
                return hallVO;
            }).collect(Collectors.toList());
            return ResponseVO.buildSuccess(result);
        } else return ResponseVO.buildFailure("获取可操作的影厅列表失败！请重试～");
    }

    @Override
    public Hall getHallById(int id) {
        try {
            return hallMapper.selectHallById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private List<HallVO> hallList2HallVOList(List<Hall> hallList) {
        List<HallVO> hallVOList = new ArrayList<>();
        for (Hall hall : hallList) {
            hallVOList.add(new HallVO(hall));
        }
        return hallVOList;
    }
}
