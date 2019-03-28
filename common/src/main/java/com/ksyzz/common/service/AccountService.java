package com.ksyzz.common.service;

import com.ksyzz.common.dto.AccountDTO;

/**
 * @author fengqian
 * @since <pre>2019/03/26</pre>
 */
public interface AccountService {
    void save(AccountDTO accountDTO);
    void init();
    void consumer(int value);
}
