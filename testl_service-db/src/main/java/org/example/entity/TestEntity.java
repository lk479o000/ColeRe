package org.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author huopengbo
 * @version 1.0
 * @date 2020-06-13 19:08
 */
@Data
@TableName(value = "tbl_user", schema = "bkex_exchange")
public class TestEntity {

    @TableId
    private String id;

    private String tel;

    private String email;

}
