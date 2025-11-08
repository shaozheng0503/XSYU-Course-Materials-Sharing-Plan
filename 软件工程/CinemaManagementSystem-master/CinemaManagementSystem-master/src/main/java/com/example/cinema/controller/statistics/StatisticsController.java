package com.example.cinema.controller.statistics;

import com.example.cinema.bl.statistics.MovieLikeService;
import com.example.cinema.bl.statistics.StatisticsService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author fjj
 * @date 2019/4/16 1:34 PM
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private MovieLikeService movieLikeService;

    @RequestMapping(value = "/scheduleRate", method = RequestMethod.GET)
    public ResponseVO getScheduleRateByDate(@RequestParam(required = false) Date date){
        //此处date为非必填参数，若不填则默认为当天
        return statisticsService.getScheduleRateByDate(date);
    }

    @RequestMapping(value = "/boxOffice/total", method = RequestMethod.GET)
    public ResponseVO getTotalBoxOffice(){
        return statisticsService.getTotalBoxOffice();
    }

    // 获取前十票房的电影
    @RequestMapping(value = "/boxOffice/top10", method = RequestMethod.GET)
    public ResponseVO getTotalBoxOfficeTop10(){
        return statisticsService.getTotalBoxOfficeTop10();
    }

    @RequestMapping(value = "/audience/price", method = RequestMethod.GET)
    public ResponseVO getAudiencePrice(){
        return statisticsService.getAudiencePriceSevenDays();
    }

    @RequestMapping(value = "/PlacingRate", method = RequestMethod.GET)
    public ResponseVO getMoviePlacingRateByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy/MM/dd") Date date){
        return statisticsService.getMoviePlacingRateByDate(date);
    }

    @RequestMapping(value = "/popular/movie", method = RequestMethod.GET)
    public ResponseVO getPopularMovies(@RequestParam int days, @RequestParam int movieNum){
        return statisticsService.getPopularMovies(days, movieNum);
    }

    @RequestMapping(value = "/movieLike/top", method = RequestMethod.GET)
    public ResponseVO getMovieLikeTop(){
        return movieLikeService.getMovieLikeTop10();
    }
}
