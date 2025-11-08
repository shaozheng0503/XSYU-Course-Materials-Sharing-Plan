package com.example.cinema.blImpl.statistics;

import com.example.cinema.bl.statistics.StatisticsService;
import com.example.cinema.blImpl.management.hall.HallServiceForBl;
import com.example.cinema.blImpl.management.movie.MovieServiceForBl;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.blImpl.sales.TicketServiceForBl;
import com.example.cinema.data.statistics.StatisticsMapper;
import com.example.cinema.po.AudiencePrice;
import com.example.cinema.po.MovieScheduleTime;
import com.example.cinema.po.MovieTotalBoxOffice;
import com.example.cinema.po.ScheduleItem;
import com.example.cinema.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author fjj
 * @date 2019/4/16 1:34 PM
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    public final static Logger logger = LoggerFactory.getLogger("****Statistics****");
    @Autowired
    private StatisticsMapper statisticsMapper;
    @Autowired
    private HallServiceForBl hallServiceForBl;
    @Autowired
    private ScheduleServiceForBl scheduleServiceForBl;
    @Autowired
    private MovieServiceForBl movieServiceForBl;
    @Autowired
    private TicketServiceForBl ticketServiceForBl;

    @Override
    public ResponseVO getScheduleRateByDate(Date date) {
        try {
            Date requireDate = date;
            if (requireDate == null) {
                requireDate = new Date();
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            requireDate = simpleDateFormat.parse(simpleDateFormat.format(requireDate));

            Date nextDate = getNumDayAfterDate(requireDate, 1);
            return ResponseVO.buildSuccess(movieScheduleTimeList2MovieScheduleTimeVOList(statisticsMapper.selectMovieScheduleTimes(requireDate, nextDate)));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getTotalBoxOffice() {
        try {
            return ResponseVO.buildSuccess(movieTotalBoxOfficeList2MovieTotalBoxOfficeVOList(statisticsMapper.selectMovieTotalBoxOffice()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getTotalBoxOfficeTop10() {
        try {
            return ResponseVO.buildSuccess(movieTotalBoxOfficeList2MovieTotalBoxOfficeVOList(statisticsMapper.selectMovieTotalBoxOfficeTop10()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getAudiencePriceSevenDays() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date today = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            Date startDate = getNumDayAfterDate(today, -6);
            List<AudiencePriceVO> audiencePriceVOList = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                AudiencePriceVO audiencePriceVO = new AudiencePriceVO();
                Date date = getNumDayAfterDate(startDate, i);
                audiencePriceVO.setDate(date);
                List<AudiencePrice> audiencePriceList = statisticsMapper.selectAudiencePrice(date, getNumDayAfterDate(date, 1));
                double totalPrice = audiencePriceList.stream().mapToDouble(item -> item.getTotalPrice()).sum();
                audiencePriceVO.setPrice(Double.parseDouble(String.format("%.2f", audiencePriceList.size() == 0 ? 0 : totalPrice / audiencePriceList.size())));
                audiencePriceVOList.add(audiencePriceVO);
            }
            return ResponseVO.buildSuccess(audiencePriceVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getMoviePlacingRateByDate(Date date) {
        try {
            Date nextDate = getNumDayAfterDate(date, 1);
            List<PlacingRateVO> res = new ArrayList<>();
            List<ScheduleItem> scheduleItems = scheduleServiceForBl.getScheduleBetweenDays(date, nextDate);
            Map<String, int[]> map = new LinkedHashMap<>();
            for (ScheduleItem item : scheduleItems) {
                logger.info(movieServiceForBl.getMovieById(item.getMovieId()).getName());
                int[] arr = new int[2];
                arr[0] = ticketServiceForBl.getTicketNumBySchedule(item.getId());
                arr[1] = hallServiceForBl.getHallById(item.getHallId()).getSeatsNum();
                String movieName = movieServiceForBl.getMovieById(item.getMovieId()).getName();
                if (map.containsKey(movieName)) {
                    arr[0] += map.get(movieName)[0];
                    arr[1] += map.get(movieName)[1];
                }
                map.put(movieName, arr);
            }
            for (String name : map.keySet()) {
                PlacingRateVO vo = new PlacingRateVO();
                vo.setName(name);
                vo.setPlacingRate((double) map.get(name)[0] / (double) map.get(name)[1]);
                res.add(vo);
            }
            return ResponseVO.buildSuccess(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("获取上座率失败!");
        }
    }

    @Override
    public ResponseVO getPopularMovies(int days, int movieNum) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date today = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
            Date startDate = getNumDayAfterDate(today, -days + 1);
            List<MovieTotalBoxOffice> list = statisticsMapper.selectRecentTotalBoxOffice(startDate, today, movieNum);
            List<MovieLikeMostVO> res = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getBoxOffice() != 0) {
                    MovieLikeMostVO vo = new MovieLikeMostVO();
                    vo.setMovieId(list.get(i).getMovieId());
                    vo.setName(list.get(i).getName());
                    vo.setBoxOffice(list.get(i).getBoxOffice());
                    vo.setPosterUrl(movieServiceForBl.getMovieById(list.get(i).getMovieId()).getPosterUrl());
                    res.add(vo);
                }
            }
            return ResponseVO.buildSuccess(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("受欢迎电影获取失败！");
        }
    }

    /**
     * 获得num天后的日期
     *
     * @param oldDate
     * @param num
     * @return
     */
    Date getNumDayAfterDate(Date oldDate, int num) {
        Calendar calendarTime = Calendar.getInstance();
        calendarTime.setTime(oldDate);
        calendarTime.add(Calendar.DAY_OF_YEAR, num);
        return calendarTime.getTime();
    }

    private List<MovieScheduleTimeVO> movieScheduleTimeList2MovieScheduleTimeVOList(List<MovieScheduleTime> movieScheduleTimeList) {
        List<MovieScheduleTimeVO> movieScheduleTimeVOList = new ArrayList<>();
        for (MovieScheduleTime movieScheduleTime : movieScheduleTimeList) {
            movieScheduleTimeVOList.add(new MovieScheduleTimeVO(movieScheduleTime));
        }
        return movieScheduleTimeVOList;
    }


    private List<MovieTotalBoxOfficeVO> movieTotalBoxOfficeList2MovieTotalBoxOfficeVOList(List<MovieTotalBoxOffice> movieTotalBoxOfficeList) {
        List<MovieTotalBoxOfficeVO> movieTotalBoxOfficeVOList = new ArrayList<>();
        for (MovieTotalBoxOffice movieTotalBoxOffice : movieTotalBoxOfficeList) {
            movieTotalBoxOfficeVOList.add(new MovieTotalBoxOfficeVO(movieTotalBoxOffice));
        }
        return movieTotalBoxOfficeVOList;
    }
}
