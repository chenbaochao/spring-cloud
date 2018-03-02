package com.play001.cloud.product.api.serivce;

import com.play001.cloud.product.api.mapper.CommentMapper;
import com.play001.cloud.support.entity.Comment;
import com.play001.cloud.support.entity.Pagination;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    //分页
    public ResponseEntity<Pagination<Comment>> getList(Long productId, Long offset, Integer limit){
        ResponseEntity<Pagination<Comment>> responseEntity = new ResponseEntity<>();
        if(productId == null || offset == null || limit == null){
            return responseEntity.setErrMsg("参数错误");
        }
        Pagination<Comment> pagination = new Pagination<>();
        Long total = commentMapper.countByProductId(productId);
        pagination.setTotalData(total);
        List<Comment> comments = commentMapper.getList(productId, offset, limit);
        pagination.setData(comments);
        return responseEntity.setMessage(pagination);
    }
}
