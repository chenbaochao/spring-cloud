package com.play001.cloud.os.controller;

import com.play001.cloud.os.service.*;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.NavigationBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AdvertService advertService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private NavigationService navigationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) throws IException {
        //轮播广告
        model.addAttribute("sliderAdvert", advertService.getSliderAdvert());
        //轮播下面六个小链接
        model.addAttribute("navigationBars", navigationService.getChannelNavigationBars());
        //轮播下面三个小广告
        model.addAttribute("underSliderAdvert", advertService.getUnderSliderAdvert());
        return "webfront/index";
    }


    //明星产品
    @RequestMapping(value = "/starProduct", method = RequestMethod.GET)
    public String starProduct(Model model) throws IException {
        //明星产品
        model.addAttribute("starProduct", productService.getStarProduct());
        return "recommend/recommend_star";
    }


    //section
    @RequestMapping(value = "/section", method = RequestMethod.GET)
    public String hotProduct(Model model) throws IException {
        model.addAttribute("sections", sectionService.getIndexSections());
        return "webfront/section";
    }

    @RequestMapping(value = "/siteHeader", method = RequestMethod.GET)
    public String siteHeader(Model model) throws IException {
        model.addAttribute("sections",sectionService.getHeaderSections());
        model.addAttribute("categories",categoryService.findAll());
        return "common/site_header";
    }

    /**
     * 首页-顶部
     */
    @RequestMapping(value = "/siteTopBar", method = RequestMethod.GET)
    public String siteTopBar(Model model) throws IException {
        List<NavigationBar> navigationBars = navigationService.getTopBarNavigationBars();
        model.addAttribute("navigationBars", navigationBars);
        return "common/site_top_bar";
    }
    @RequestMapping(value = "/siteFooter", method = RequestMethod.GET)
    public String siteFooter(Model model)  {
        return "common/site_footer";
    }
}
