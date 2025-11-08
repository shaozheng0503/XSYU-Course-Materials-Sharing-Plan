package com.example.cinema.blImpl.management.hall;

import com.example.cinema.CinemaApplication;
import com.example.cinema.bl.management.HallService;
import com.example.cinema.po.Hall;
import com.example.cinema.vo.HallVO;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = CinemaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HallServiceImplTest {
    @Autowired
    private HallService hallService;

    @Test
    public void addHall() {
        HallVO hall = new HallVO();
        int[][] seats = new int[3][];
        seats[0] = new int[]{0, 0, 0, 0, 0};
        seats[1] = new int[]{0, -1, -1, -1, 0};
        seats[2] = new int[]{0, 0, 0, 0, 0};
        hall.setName("VIP配置67肥宅快乐水厅");
        hall.setSeats(seats);
        hall.setScale(Hall.HALL_SCALE_SMALL);
        Assert.assertEquals(1, hallService.addHall(hall));
    }

    @Test
    public void updateHall() {
    }

    @Test
    public void removeHall() {
    }

    @Test
    public void getAvailableHalls() {
    }

    @Test
    public void getHallById() {
    }
}
