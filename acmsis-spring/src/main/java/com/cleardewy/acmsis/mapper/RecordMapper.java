package com.cleardewy.acmsis.mapper;

import com.cleardewy.acmsis.entity.Record;
import com.cleardewy.acmsis.entity.TimeCount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @Author: cleardewy
 * @Date: 2022/10/13 15:25
 */
@Mapper
public interface RecordMapper {

    Integer AddRecord(Record record) throws DataAccessException;

    List<TimeCount> CountTime(TimeCount timeCount) throws DataAccessException;
}
