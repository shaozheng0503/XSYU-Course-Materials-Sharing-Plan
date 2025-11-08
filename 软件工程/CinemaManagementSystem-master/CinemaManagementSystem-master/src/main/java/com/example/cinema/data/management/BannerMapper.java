package com.example.cinema.data.management;

import com.example.cinema.po.BannerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author hxw
 * @date 2019-6-10
 */
@Mapper
public interface BannerMapper {

    BannerInfo selectBannerInfo();

    int updateBannerInfo(@Param("info") BannerInfo info);
}
