package com.ksyzz.record;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fengqian
 * @since <pre>2019/03/26</pre>
 */
@SpringBootApplication(scanBasePackages = "com.ksyzz")
public class RecordApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecordApplication.class, args);
    }
}
