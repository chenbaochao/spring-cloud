package com.play001.cloud.os.controller;

import com.play001.cloud.common.entity.IException;
import com.play001.cloud.os.service.impl.AdvertServiceImpl;
import com.play001.cloud.os.service.impl.CategoryServiceImpl;
import com.play001.cloud.os.service.impl.ProductServiceImpl;
import com.play001.cloud.os.service.impl.SectionServiceImpl;
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
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private SectionServiceImpl sectionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) throws IException {

        //轮播广告
        model.addAttribute("sliderAdvert", advertService.getSliderAdvert());
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
        model.addAttribute("sections", sectionService.getSection());
        return "webfront/section";
    }

    @RequestMapping(value = "/siteHeader", method = RequestMethod.GET)
    public String siteHeader(Model model) throws IException {
        model.addAttribute("categories", categoryService.findAllWithProduct());
        return "common/site_header";
    }
    @RequestMapping(value = "/siteTopBar", method = RequestMethod.GET)
    public String siteTopBar(Model model)  {
        return "common/site_top_bar";
    }
    @RequestMapping(value = "/siteFooter", method = RequestMethod.GET)
    public String siteFooter(Model model)  {
        return "common/site_footer";
    }
}
