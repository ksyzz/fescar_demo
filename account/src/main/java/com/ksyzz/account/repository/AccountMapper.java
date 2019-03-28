package com.ksyzz.account.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ksyzz.account.model.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author fengqian
 * @since <pre>2019/03/26</pre>
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
