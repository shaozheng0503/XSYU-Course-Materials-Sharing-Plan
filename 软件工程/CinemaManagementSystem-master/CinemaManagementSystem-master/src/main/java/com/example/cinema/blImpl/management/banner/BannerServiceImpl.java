package com.example.cinema.blImpl.management.banner;

import com.example.cinema.bl.management.banner.BannerService;
import com.example.cinema.data.management.BannerMapper;
import com.example.cinema.po.BannerInfo;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hxw
 * @date 2019-6-10
 */
@Service
public class BannerServiceImpl implements BannerService{

    @Autowired
    BannerMapper bannerMapper;

    @Override
    public ResponseVO getBannerInfo() {
        try {
            return ResponseVO.buildSuccess(bannerMapper.selectBannerInfo());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("获取轮播信息失败!");
        }
    }

    @Override
    public ResponseVO updateBannerInfo(BannerInfo info) {
        try {
            if (bannerMapper.updateBannerInfo(info) == 1 ) {
                return ResponseVO.buildSuccess();
            } else {
                return ResponseVO.buildFailure("无法修改轮播信息!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("修改轮播信息失败!");
        }
    }
}
