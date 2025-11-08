package com.example.cinema.blImpl.management.schedule;

import com.example.cinema.CinemaApplication;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CinemaApplication.class})
@FixMethodOrder(MethodSorters.JVM)
public class ScheduleServiceForBlTest {
    @Autowired
    private ScheduleServiceForBl scheduleServiceForBl;

    @Test
    public void getScheduledHalls() {
        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
        Assert.assertEquals(list, scheduleServiceForBl.getScheduledHalls());
    }
}
