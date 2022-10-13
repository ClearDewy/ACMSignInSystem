package com.qingtuan.acmsis.mapper;

import com.qingtuan.acmsis.entity.Record;
import com.qingtuan.acmsis.entity.TimeCount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @Author: Qingtuan
 * @Date: 2022/10/13 15:25
 */
@Mapper
public interface RecordMapper {

    Integer AddRecord(Record record) throws DataAccessException;

    List<TimeCount> CountTime(TimeCount timeCount) throws DataAccessException;
}
