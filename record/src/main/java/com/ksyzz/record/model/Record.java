package com.ksyzz.record.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author fengqian
 * @since <pre>2019/03/26</pre>
 */
@Data
@TableName(value = "t_record")
public class Record {
    @TableId(type = IdType.AUTO)
    private long id;
    @TableField
    private String account;
    @TableField
    private int number;

}
