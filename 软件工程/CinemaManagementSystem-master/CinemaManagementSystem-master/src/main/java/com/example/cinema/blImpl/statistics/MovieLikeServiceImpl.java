package com.example.cinema.blImpl.statistics;

import java.util.ArrayList;
import java.util.List;

import com.example.cinema.bl.statistics.MovieLikeService;
import com.example.cinema.blImpl.management.movie.MovieServiceForBl;
import com.example.cinema.data.statistics.MovieLikeMapper;
import com.example.cinema.po.DateLike;
import com.example.cinema.po.Movie;
import com.example.cinema.po.MovieLike;
import com.example.cinema.vo.DateLikeVO;
import com.example.cinema.vo.MovieLikeVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fjj
 * @date 2019/4/28 3:07 PM
 */
@Service
public class MovieLikeServiceImpl implements MovieLikeService {
    private static final String ALREADY_LIKE_ERROR_MESSAGE = "用户已标记该电影为想看";
    private static final String UNLIKE_ERROR_MESSAGE = "用户未标记该电影为想看";
    private static final String MOVIE_NOT_EXIST_ERROR_MESSAGE = "电影不存在";

    @Autowired
    private MovieLikeMapper movieLikeMapper;
    @Autowired
    private MovieServiceForBl movieServiceForBl;

    @Override
    public ResponseVO likeMovie(int userId, int movieId) {

        if (userLikeTheMovie(userId, movieId)) {
            return ResponseVO.buildFailure(ALREADY_LIKE_ERROR_MESSAGE);
        } else if (movieServiceForBl.getMovieById(movieId) == null) {
            return ResponseVO.buildFailure(MOVIE_NOT_EXIST_ERROR_MESSAGE);
        }
        try {
            return ResponseVO.buildSuccess(movieLikeMapper.insertOneLike(movieId, userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO unLikeMovie(int userId, int movieId) {
        if (!userLikeTheMovie(userId, movieId)) {
            return ResponseVO.buildFailure(UNLIKE_ERROR_MESSAGE);
        } else if (movieServiceForBl.getMovieById(movieId) == null) {
            return ResponseVO.buildFailure(MOVIE_NOT_EXIST_ERROR_MESSAGE);
        }
        try {
            return ResponseVO.buildSuccess(movieLikeMapper.deleteOneLike(movieId, userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }

    @Override
    public ResponseVO getCountOfLikes(int movieId) {
        try {
            return ResponseVO.buildSuccess(movieLikeMapper.selectLikeNums(movieId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getLikeNumsGroupByDate(int movieId) {
        try {
            return ResponseVO.buildSuccess(dateLikeList2DateLikeVOList(movieLikeMapper.getDateLikeNum(movieId)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getMovieLikeTop10() {
        List<MovieLike> movieLikes = movieLikeMapper.getMovieLikeTop(10);
        List<MovieLikeVO> movieLikeMostVOs = new ArrayList<>();
        movieLikes.forEach(movieLike -> {
            MovieLikeVO movieLikeVO = new MovieLikeVO();
            movieLikeVO.setMovieId(movieLike.getMovieId());
            movieLikeVO.setLikeCount(movieLike.getCount());
            Movie movie = movieServiceForBl.getMovieById(movieLike.getMovieId());
            movieLikeVO.setName(movie.getName());
            movieLikeVO.setPosterUrl(movie.getPosterUrl());
            movieLikeMostVOs.add(movieLikeVO);
        });

        try {
            return ResponseVO.buildSuccess(movieLikeMostVOs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("获取想看人数列表失败！");
        }
    }

    private boolean userLikeTheMovie(int userId, int movieId) {
        return movieLikeMapper.selectLikeMovie(movieId, userId) != 0;
    }


    private List<DateLikeVO> dateLikeList2DateLikeVOList(List<DateLike> dateLikeList) {
        List<DateLikeVO> dateLikeVOList = new ArrayList<>();
        for (DateLike dateLike : dateLikeList) {
            dateLikeVOList.add(new DateLikeVO(dateLike));
        }
        return dateLikeVOList;
    }
}
