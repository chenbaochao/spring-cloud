package com.play001.cloud.product.api.controller;

import com.play001.cloud.product.api.serivce.CommentService;
import com.play001.cloud.support.entity.Comment;
import com.play001.cloud.support.entity.Pagination;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/comment")
public class CommentEndPoint {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public ResponseEntity<Pagination<Comment>> getList(Long productId, Long offset, Integer limit){
        return commentService.getList(productId, offset, limit);
    }
}
