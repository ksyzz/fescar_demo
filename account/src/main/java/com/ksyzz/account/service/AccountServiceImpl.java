package com.ksyzz.account.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fescar.core.context.RootContext;
import com.ksyzz.account.model.Account;
import com.ksyzz.account.repository.AccountMapper;
import com.ksyzz.common.dto.AccountDTO;
import com.ksyzz.common.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author fengqian
 * @since <pre>2019/03/26</pre>
 */
@Component
@Service(interfaceClass = AccountService.class)
public class AccountServiceImpl implements AccountService {
    @Resource
    AccountMapper accountMapper;
    @Override
    public void save(AccountDTO accountDTO) {
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO, account);
        accountMapper.insert(account);
    }

    @Override
    public void init() {
        Account account = accountMapper.selectById("admin");
        if (account == null) {
            Account account1 = new Account();
            account1.setId("admin");
            account1.setLast(2000);
            accountMapper.insert(account1);
        }
    }

    @Override
    public void consumer(int value) {
        System.out.println("全局事务id ：" + RootContext.getXID());
        Account account = accountMapper.selectById("admin");
        account.setLast(account.getLast() - value);
        accountMapper.updateById(account);
    }
}
