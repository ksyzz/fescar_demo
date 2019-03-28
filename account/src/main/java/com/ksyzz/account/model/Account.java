package com.ksyzz.account.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author fengqian
 * @since <pre>2019/03/26</pre>
 */
@Data
@TableName(value = "t_account")
public class Account{
    @TableId
    private String id;
    @TableField
    private long last;

}
