package org.example.impl;

import com.alibaba.fastjson.JSON;
import com.weibi.core.commons.db.extension.service.impl.BaseServiceImpl;
import org.example.api.TestService;
import org.example.entity.TestEntity;
import org.example.mapper.TestMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author huopengbo
 * @version 1.0
 * @date 2020-06-13 19:08
 */
@Slf4j
@Service
public class TestServiceImpl extends BaseServiceImpl<TestEntity> implements TestService {

    @Resource
    private TestMapper testMapper;

    @Override
    public Object selectOne(String id) {
        // 查询一条数据 demo
        var result = lambdaQuery().select()
                .eq(TestEntity::getId, id)
                .one();
        log.info(JSON.toJSONString(result));
        return "123";
    }
}
