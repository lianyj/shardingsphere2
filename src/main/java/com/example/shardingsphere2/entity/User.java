package com.example.shardingsphere2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user")
public class User extends Model<User> {
    /**
     * 主键id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 名称
     */
    public String name;
    /**
     * 年龄
     */
    private int age;

    /**
     * 创建时间
     */
    private String createTime;

}
