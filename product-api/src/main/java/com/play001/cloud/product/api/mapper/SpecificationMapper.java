package com.play001.cloud.product.api.mapper;

import com.play001.cloud.support.entity.product.Specification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SpecificationMapper {

    @Select("")
    List<Specification> findByProductId();


    @Select("SELECT id, product_id as productId, name, sold_number as soldNumber, stock, price " +
            "  FROM os_product_specification WHERE id = #{id}")
    Specification findById(Long id);

    /**
     * 减少库存,库存必须大于0
     * @param id 规格ID
     * @return 影响行数
     */
    @Update("update os_product_specification set stock = stock-1 where id = #{id} and stock > 0")
    Integer decreaseStock(@Param("id") Long id);
}
