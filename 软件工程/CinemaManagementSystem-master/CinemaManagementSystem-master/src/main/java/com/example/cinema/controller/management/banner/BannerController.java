package com.example.cinema.controller.management.banner;

import com.example.cinema.bl.management.banner.BannerService;
import com.example.cinema.po.BannerInfo;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hxw
 * @date 2019-6-10
 */
@RestController
public class BannerController {

    @Autowired
    BannerService bannerService;

    @GetMapping("/banner/get")
    public ResponseVO getBannerInfo() {
        return bannerService.getBannerInfo();
    }

    @PostMapping("/banner/update")
    public ResponseVO updateBannerInfo(@RequestBody BannerInfo info) {
        return bannerService.updateBannerInfo(info);
    }
}
