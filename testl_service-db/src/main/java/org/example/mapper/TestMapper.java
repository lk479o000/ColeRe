package org.example.mapper;

import com.weibi.core.commons.db.extension.mapper.BaseDao;
import org.example.entity.TestEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author huopengbo
 * @version 1.0
 * @date 2020-06-13 19:10
 */
@Mapper
public interface TestMapper extends BaseDao<TestEntity> {


}
