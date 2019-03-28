package com.ksyzz.record.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fescar.core.context.RootContext;
import com.ksyzz.common.service.RecordService;
import com.ksyzz.record.model.Record;
import com.ksyzz.record.repository.RecordMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author fengqian
 * @since <pre>2019/03/26</pre>
 */
@Component
@Service(interfaceClass = RecordService.class)
public class RecordServiceImpl implements RecordService {

    @Resource
    RecordMapper recordMapper;

    @Override
    public void record(int value, String id) throws Exception {
        System.out.println("全局事务id ：" + RootContext.getXID());

        Record record = new Record();
        record.setAccount(id);
        record.setNumber(value);
        if (value < 2000) {
            recordMapper.insert(record);
        } else {
            throw new Exception("RECORD");
        }
    }
}
