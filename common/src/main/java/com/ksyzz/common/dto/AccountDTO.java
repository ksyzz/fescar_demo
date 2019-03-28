package com.ksyzz.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fengqian
 * @since <pre>2019/03/26</pre>
 */
@Data
public class AccountDTO implements Serializable {
    private String id;
    private long last;
}
