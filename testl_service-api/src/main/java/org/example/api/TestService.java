package org.example.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author huopengbo
 * @version 1.0
 * @date 2020-06-13 19:06
 */
@ApiOperation("测试服务")
@FeignClient(name = "${feign.testl_service}")
public interface TestService {

    @GetMapping("/selectOne")
    @ApiOperation(value = "id查询", notes = "id查询")
    Object selectOne(@ApiParam(value = "id") @RequestParam String id);

}
