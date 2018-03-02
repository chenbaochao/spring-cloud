package com.play001.cloud.os.service;

import com.play001.cloud.os.mapper.CommentMapper;
import com.play001.cloud.support.entity.Comment;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.Pagination;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    //获取评论
    public Pagination<Comment> getList(Long productId, Long offset, Integer limit) throws IException {
        if(productId == null || offset == null || limit == null){
            throw new IException("参数错误");
        }
        ResponseEntity<Pagination<Comment>> responseEntity = commentMapper.getList(productId,offset, limit);
        if(responseEntity.getStatus().equals(ResponseEntity.ERROR)){
            throw new IException(responseEntity.getErrMsg());
        }
        return responseEntity.getMessage();
    }

}
