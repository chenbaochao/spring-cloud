package com.play001.cloud.os.controller;

import com.play001.cloud.support.entity.IException;
import com.play001.cloud.os.service.CategoryService;
import com.play001.cloud.os.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/detail-{id}")
    public String detail(@PathVariable Long id, Model model) throws IException {
        model.addAttribute("product",productService.getDetail(id));
        return "product/detail";
    }
    @RequestMapping("/search")
    public String search(String keyword, Integer pageNo, Model model) throws IException {
        model.addAttribute("pagination", productService.getPaginationBySearch(pageNo, keyword));
        return "product/search";
    }

    /**
     * 分类列出产品
     * @param categoryId 产品目录ID
     * @param sort 排序方式,1.新品,2.销量,3.价格up,4.价格down
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Integer categoryId, Integer sort, Integer pageNo, Model model) throws IException {
        if(categoryId != null && categoryId < 0) throw new IException("分类不存在");
        sort = (sort != null && sort > 0 && sort < 5)?sort:1;
        pageNo = (pageNo != null && pageNo > 0)?pageNo:1;
        model.addAttribute("pagination", productService.getPaginationByCategoryId(categoryId, sort, pageNo));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("sort", sort);
        model.addAttribute("category", categoryService.findById(categoryId));
        model.addAttribute("url","/product/list?categoryId="+categoryId+"&sort="+sort+"&pageNo=");
        return "product/list";
    }

}
