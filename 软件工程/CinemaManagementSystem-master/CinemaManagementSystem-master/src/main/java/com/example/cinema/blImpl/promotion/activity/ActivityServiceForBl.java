package com.example.cinema.blImpl.promotion.activity;

import com.example.cinema.po.Activity;
import com.example.cinema.vo.ResponseVO;

import java.util.List;

public interface ActivityServiceForBl {

    ResponseVO getActivitiesByMovie(int movieId);

    List<Activity> getActivityList();
}
