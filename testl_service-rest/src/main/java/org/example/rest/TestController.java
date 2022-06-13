package org.example.rest;

import com.google.common.base.Preconditions;
import com.weibi.core.language.util.LanguageUtils;
import org.example.api.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author huopengbo
 * @version 1.0
 * @date 2020-06-13 19:12
 */
@Slf4j
@RestController
public class TestController implements TestService {

    @Resource
    private TestService testServiceImpl;

    @Override
    public Object selectOne(String id) {
        // 参数校验
        Preconditions.checkArgument(
                id != null,
                LanguageUtils.getGlobalizationMessage("0010")
        );
        // 执行业务逻辑
        return testServiceImpl.selectOne(id);
    }
}
