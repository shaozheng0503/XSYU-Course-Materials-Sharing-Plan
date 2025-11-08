package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.ActivityService;
import com.example.cinema.vo.ActivityForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by liying on 2019/4/20.
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @PostMapping("/publish")
    public ResponseVO publishActivity(@RequestBody ActivityForm activityForm){
        return activityService.publishActivity(activityForm);
    }
    @GetMapping("/getAll")
    public ResponseVO getActivities(){
        return activityService.getActivities();
    }

    @GetMapping("/delete")
    public ResponseVO deleteActivity(@RequestParam int activityId){
        return activityService.deleteActivity(activityId);
    }

    @GetMapping("/get")
    public ResponseVO getActivityById(@RequestParam int activityId){
        return activityService.getActivityById(activityId);
    }

    @GetMapping("/getByMovieId")
    public ResponseVO getActivitiesById(@RequestParam int movieId){
        return activityService.getActivitiesByMovie(movieId);
    }

}
