package com.play001.cloud.os.mapper;

import com.play001.cloud.support.entity.Comment;
import com.play001.cloud.support.entity.Pagination;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ZUUL")
public interface CommentMapper {


    /**
     * 获取评论
     * @param productId 产品Id
     * @param offset 起始位置
     * @param limit 数据条数
     * @return
     */
    @RequestMapping(value = "/product/comment/getList", method = RequestMethod.GET)
    ResponseEntity<Pagination<Comment>> getList(@RequestParam("productId")Long productId, @RequestParam("offset")Long offset, @RequestParam("limit") Integer limit);
}
