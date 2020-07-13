package com.wang.gmall.model.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 叶瑟
 */
@Data
public class BaseEntity implements Serializable {

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
