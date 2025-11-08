package com.example.cinema.controller.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author deng
 * @date 2019/03/11
 */
@Controller
public class ViewController {
    @RequestMapping(value = "/index")
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "/signUp")
    public String getSignUp() {
        return "signUp";
    }

    @RequestMapping(value = "/admin/movie/manage")
    public String getAdminMovieManage() {
        return "adminMovieManage";
    }

    @RequestMapping(value = "/admin/session/manage")
    public String getAdminSessionManage() {
        return "adminScheduleManage";
    }

    @RequestMapping(value = "/admin/cinema/manage")
    public String getAdminCinemaManage() {
        return "adminCinemaManage";
    }

    @RequestMapping(value = "/admin/promotion/manage")
    public String getAdminPromotionManage() {
        return "adminPromotionManage";
    }

    @RequestMapping(value = "/admin/cinema/statistic")
    public String getAdminCinemaStatistic() {
        return "adminCinemaStatistic";
    }

    @RequestMapping(value = "/admin/movieDetail")
    public String getAdminMovieDetail(@RequestParam int id) { return "adminMovieDetail"; }

    @RequestMapping(value = "/admin/coupon/present")
    public String getCouponPresent() {
        return "adminCouponPresent";
    }

    @RequestMapping(value = "/admin/card/manage")
    public String getAdminCardManage() {
        return "adminCardManage";
    }

    @RequestMapping(value = "/user/home")
    public String getUserHome() {
        return "userHome";
    }

    @RequestMapping(value = "/user/buy")
    public String getUserBuy() {
        return "userBuy";
    }

    @RequestMapping(value = "/user/movieDetail")
    public String getUserMovieDetail(@RequestParam int id) {
        return "userMovieDetail";
    }

    @RequestMapping(value = "/user/movieDetail/buy")
    public String getUserMovieBuy(@RequestParam int id) {
        return "userMovieBuy";
    }

    @RequestMapping(value = "/user/movie")
    public String getUserMovie() {
        return "userMovie";
    }

    @RequestMapping(value = "/user/member")
    public String getUserMember() {
        return "userMember";
    }

    @RequestMapping(value = "/user/edit")
    public String getUserEdit() {
        return "userEdit";
    }

    @RequestMapping(value = "/admin/refund/manage")
    public String getAdminRefundManage(){
        return "adminRefundManage";
    }

    @RequestMapping(value = "/admin/role/manage")
    public String getAdminRoleManage() {
        return "adminRoleManage";
    }

    @RequestMapping(value = "/user/history/topup")
    public String getUserTopUpHistory() {
        return "userTopUpHistory";
    }

    @RequestMapping(value = "/user/history/consume")
    public String getUserConsumeHistory() {
        return "userConsumeHistory";
    }

    @RequestMapping(value = "/user/history/consume/detail")
    public String getConsumeHisDetail(@RequestParam("id") Integer id) {
        return "consumeHisDetail";
    }

    @RequestMapping(value = "/user/lottery")
    public String getUserLottery() {
        return "userLottery";
    }

}
