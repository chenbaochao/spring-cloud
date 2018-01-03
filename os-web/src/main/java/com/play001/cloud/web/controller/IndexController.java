package com.play001.cloud.web.controller;

import com.play001.cloud.common.entity.IException;
import com.play001.cloud.web.service.impl.AdvertServiceImpl;
import com.play001.cloud.web.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private AdvertServiceImpl advertService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) throws IException {
        model.addAttribute("categories", categoryService.findAllWithProduct());
        //轮播广告
        model.addAttribute("sliderAdvert", advertService.getSliderAdvert());
        //轮播下面三个小广告
        model.addAttribute("underSliderAdvert", advertService.getUnderSliderAdvert());


        return "webfront/index";
    }
    //明星产品
    @RequestMapping(value = "/startAdvert", method = RequestMethod.GET)
    public String startAdvert(Model model) throws IException {
        //明星产品
        model.addAttribute("startAdvert", advertService.getStartAdvert());
        return "";
    }
}
