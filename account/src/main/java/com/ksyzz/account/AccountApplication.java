package com.ksyzz.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fengqian
 * @since <pre>2019/03/26</pre>
 */
@SpringBootApplication(scanBasePackages = "com.ksyzz.common.config")
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

}
