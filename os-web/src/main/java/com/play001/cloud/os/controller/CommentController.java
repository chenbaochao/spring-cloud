package com.play001.cloud.os.controller;

import com.play001.cloud.os.service.CommentService;
import com.play001.cloud.support.entity.Comment;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //产品页评论列表item,十条
    @RequestMapping(value = "/itemList", method = RequestMethod.GET)
    public String commentItemList(Model model, Long productId) throws IException {
        model.addAttribute("comments", commentService.getList(productId, 0L, 10).getData());
        return "comment/comment_item";
    }
    //评论列表全部
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model,  Long productId, Integer pageNo) throws IException {
        final int pageSize = 20;//一页显示二十条
        Long offset = (long)pageSize*(pageNo-1);
        Pagination<Comment> pagination = commentService.getList(productId, offset, pageSize);
        pagination.setPageNo(pageNo);
        Integer totalPage = (int)(pagination.getTotalData()+pageSize-1)/pageSize;
        pagination.setTotalPage(totalPage);
        model.addAttribute("pagination", pagination);
        model.addAttribute("url", "/comment/list?productId="+productId+"&pageNo=");
        return "comment/comment_list";
    }
}
