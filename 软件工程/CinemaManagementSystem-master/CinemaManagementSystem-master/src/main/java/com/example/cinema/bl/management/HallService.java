package com.example.cinema.bl.management;

import com.example.cinema.vo.HallVO;
import com.example.cinema.vo.ResponseVO;

/**
 * @author fjj, chph
 * @date 2019/4/12 2:01 PM
 */
public interface HallService {
    /**
     * 搜索所有影厅
     * @return
     */
    ResponseVO searchAllHall();

    /**
     * 录入影厅信息
     * @param hallVO
     * @return
     */
    ResponseVO addHall(HallVO hallVO);

    /**
     * 更新影厅信息
     * @param hallVO
     * @return
     */
    ResponseVO updateHall(HallVO hallVO);

    /**
     * 删除影厅
     * @param hallId
     * @return
     */
    ResponseVO removeHall(int hallId);

    /**
     * 获取没有排片的影厅
     * @return
     */
    ResponseVO getAvailableHalls();
}
