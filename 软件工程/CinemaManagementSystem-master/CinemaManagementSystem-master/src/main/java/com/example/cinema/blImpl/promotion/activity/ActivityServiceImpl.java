package com.example.cinema.blImpl.promotion.activity;

import com.example.cinema.bl.promotion.ActivityService;
import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.data.promotion.ActivityMapper;
import com.example.cinema.po.Activity;
import com.example.cinema.po.Coupon;
import com.example.cinema.vo.ActivityForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liying on 2019/4/20.
 */
@Service
public class ActivityServiceImpl implements ActivityService, ActivityServiceForBl {

    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    CouponService couponService;

    @Override
    @Transactional
    public ResponseVO publishActivity(ActivityForm activityForm) {
        try {
            ResponseVO vo = couponService.addCoupon(activityForm.getCouponForm());
            Coupon coupon = (Coupon) vo.getContent();
            Activity activity = new Activity();
            activity.setName(activityForm.getName());
            activity.setDescription(activityForm.getDescription()==null||activityForm.getDescription().equals("")? activityForm.getName(): activityForm.getDescription());
            activity.setStartTime(activityForm.getStartTime());
            activity.setEndTime(activityForm.getEndTime());
            activity.setTargetAmount(activityForm.getTargetAmount());
            activity.setCoupon(coupon);
            activityMapper.insertActivity(activity);
            if(activityForm.getMovieList()!=null&&activityForm.getMovieList().size()!=0){
                activityMapper.insertActivityAndMovie(activity.getId(), activityForm.getMovieList());
            }
            return ResponseVO.buildSuccess(activityMapper.selectById(activity.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getActivities() {
        try {
            return ResponseVO.buildSuccess(activityMapper.selectActivities().stream().map(Activity::getVO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO deleteActivity(int activityId) {
        try{
            activityMapper.deleteActivityById(activityId);
            activityMapper.deleteActivityAndMovie(activityId);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("删除活动失败");
        }
    }

    @Override
    public ResponseVO getActivitiesByMovie(int movieId) {
        try {
            List<Activity> activities=activityMapper.selectActivitiesByMovie(movieId);
            List<Activity> newActivities=new ArrayList<>();
            Timestamp timestamp=new Timestamp(System.currentTimeMillis());
            for (Activity temp:activities){
                if (timestamp.after(temp.getStartTime()) && timestamp.before(temp.getEndTime())){
                    newActivities.add(temp);
                }
            }
            return ResponseVO.buildSuccess(newActivities);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public List<Activity> getActivityList() {
        try {
            return activityMapper.selectActivities();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseVO getActivityById(int activityId){
        try{
            return ResponseVO.buildSuccess(activityMapper.selectById(activityId));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("获取活动失败");
        }
    }
}
