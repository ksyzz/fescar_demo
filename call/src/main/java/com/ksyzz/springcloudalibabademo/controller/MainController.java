package com.ksyzz.springcloudalibabademo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import com.ksyzz.common.service.AccountService;
import com.ksyzz.common.service.RecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fengqian
 * @since <pre>2019/03/26</pre>
 */
@RestController
public class MainController {
    @Reference
    AccountService accountService;
    @Reference
    RecordService recordService;

    @GetMapping("/deal/{value}")
    @GlobalTransactional(name = "demo")
    public void get(
            @PathVariable("value") int value
    ) throws Exception {
        accountService.init();
        accountService.consumer(value);
        recordService.record(value, "admin");
    }
}
