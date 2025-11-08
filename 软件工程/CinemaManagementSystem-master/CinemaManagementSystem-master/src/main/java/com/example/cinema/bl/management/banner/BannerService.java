package com.example.cinema.bl.management.banner;

import com.example.cinema.po.BannerInfo;
import com.example.cinema.vo.ResponseVO;

/**
 * @author hxw
 * @date 2019-6-10
 */
public interface BannerService {

    /**
     * 获取轮播信息
     * @return
     */
    ResponseVO getBannerInfo();

    /**
     * 更新轮播信息
     * @param info
     * @return
     */
    ResponseVO updateBannerInfo(BannerInfo info);
}
