package com.ksyzz.record.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ksyzz.record.model.Record;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author fengqian
 * @since <pre>2019/03/26</pre>
 */
@Mapper
public interface RecordMapper extends BaseMapper<Record> {
}
