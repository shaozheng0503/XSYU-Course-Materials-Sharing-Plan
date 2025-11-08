package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.sales.RefundService;
import com.example.cinema.blImpl.management.movie.MovieServiceForBl;
import com.example.cinema.data.sales.RefundMapper;
import com.example.cinema.po.Refund;
import com.example.cinema.vo.RefundForm;
import com.example.cinema.vo.RefundVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 2019-5-19
 * @author CZ
 */
@Service
public class RefundServiceImpl implements RefundService,RefundServiceForBl {

    @Autowired
    RefundMapper refundMapper;

    @Autowired
    MovieServiceForBl movieService;

    @Override
    public ResponseVO getAllRefund() {
        try{
            List<Refund> refundList = refundMapper.selectRefund();
            List<RefundVO> refundVOList = new ArrayList<>();
            for(Refund refund:refundList){
                RefundVO refundVO=new RefundVO();
                refundVO.setId(refund.getId());
                refundVO.setMovie( movieService.getMovieById(refund.getMovieId()));
                refundVO.setTime(refund.getTime());
                refundVO.setPrice(refund.getPrice());
                refundVOList.add(refundVO);
            }
            return ResponseVO.buildSuccess(refundVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("获取退票策略失败!");
        }
    }

    @Override
    public ResponseVO publishRefund(RefundForm refundForm) {
        if(prePublishRefund(refundForm)){
            return ResponseVO.buildSuccess();
        }else{
            return ResponseVO.buildFailure("添加退票策略失败！");
        }
    }

    @Override
    public ResponseVO deleteRefund(int refundId) {
        if(preDeleteRefund(refundId)){
            return ResponseVO.buildSuccess();
        }else{
            return ResponseVO.buildFailure("删除退票策略失败");
        }
    }

    @Override
    public ResponseVO getRefundById(int refundId) {
        try{
            Refund refund=refundMapper.selectRefundById(refundId);
            RefundVO refundVO=new RefundVO();
            refundVO.setId(refund.getId());
            refundVO.setMovie(movieService.getMovieById(refund.getMovieId()));
            refundVO.setTime(refund.getTime());
            refundVO.setPrice(refund.getPrice());
            return ResponseVO.buildSuccess(refundVO);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("获取退票策略失败");
        }
    }

    private boolean prePublishRefund(RefundForm refundForm){
        try{
            List<Integer> movieIds=refundForm.getMovieList();
            for(int i=0;i<movieIds.size();i++){
                Refund refund=new Refund();
                refund.setMovieId(movieIds.get(i));
                refund.setTime(refundForm.getTime());
                refund.setPrice(refundForm.getPrice());
                refundMapper.insertRefund(refund);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private boolean preDeleteRefund(int refundId){
        try{
            refundMapper.deleteRefundById(refundId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Refund> getRefundByMovieId(int movieId) {
        try{
            return refundMapper.selectRefundByMovieId(movieId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
