package com.example.cinema.management;

import com.example.cinema.CinemaApplication;
import com.example.cinema.data.management.HallMapper;
import com.example.cinema.po.Hall;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CinemaApplication.class})
@FixMethodOrder(MethodSorters.JVM)
public class HallMapperTest {
    @Autowired
    private HallMapper hallMapper;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addHall() {
        Hall hall = new Hall();
        int[][] seats = new int[3][];
        seats[0] = new int[]{0, 0, 0, 0, 0};
        seats[1] = new int[]{0, -1, -1, -1, 0};
        seats[2] = new int[]{0, 0, 0, 0, 0};
        hall.setName("VIP配置67肥宅快乐水厅");
        hall.setGeneratedSeats(seats);
        hall.setScale(Hall.HALL_SCALE_SMALL);
        Assert.assertEquals(1, hallMapper.addHall(hall));
    }

    @Test
    public void updateHall() {
        Hall hall = new Hall();
        int[][] seats = new int[3][];
        seats[0] = new int[]{0, 0, 0, 0, 0};
        seats[1] = new int[]{0, -1, 0, -1, 0};
        seats[2] = new int[]{0, 0, 0, 0, 0};
        hall.setName("67肥宅快乐水厅");
        hall.setGeneratedSeats(seats);
        hall.setScale(Hall.HALL_SCALE_SMALL);
        hall.setName("67肥宅快乐水私人厅");
        hallMapper.addHall(hall);
        Assert.assertEquals(1, hallMapper.updateHall(hall));
        Assert.assertEquals("67肥宅快乐水私人厅", hallMapper.selectHallById(hall.getId()).getName());
    }

    @Test
    public void removeHallById() {
        Hall hall = new Hall();
        int[][] seats = new int[3][];
        seats[0] = new int[]{0, 0, -1, 0, 0};
        seats[1] = new int[]{0, -1, 0, -1, 0};
        seats[2] = new int[]{0, 0, -1, 0, 0};
        hall.setName("67快乐厅");
        hall.setGeneratedSeats(seats);
        hall.setScale(Hall.HALL_SCALE_SMALL);
        hallMapper.addHall(hall);
        Assert.assertEquals(1, hallMapper.removeHallById(hall.getId()));
        Assert.assertEquals(null, hallMapper.selectHallById(hall.getId()));
    }
}
