package com.cleardewy.acmsis.service.Impl;

import com.cleardewy.acmsis.entity.JsonResult;
import com.cleardewy.acmsis.entity.Record;
import com.cleardewy.acmsis.entity.TimeCount;
import com.cleardewy.acmsis.mapper.RecordMapper;
import com.cleardewy.acmsis.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import static com.cleardewy.acmsis.constant.ErorrMessage.CountTimeFail;
import static com.cleardewy.acmsis.constant.ErorrMessage.TimeError;

/**
 * @Author: cleardewy
 * @Date: 2022/10/13 19:24
 */
@Service
@Slf4j
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public Integer AddRecord(Record record) {
        return recordMapper.AddRecord(record);
    }

    @Override
    public JsonResult CountTime(TimeCount timeCount) {
        List<TimeCount> timeCounts;
        try {
            // 天数加一
            Date date = new Date(timeCount.getEndDate().getTime() + 24 * 60 * 60 * 1000);
            timeCount.setEndDate(date);
        } catch (DataAccessException e) {
            log.error("{}--->{}", TimeError,e.getMessage());
            return new JsonResult(false, TimeError.toString(), e.getMessage());
        }
        try {
            timeCounts = recordMapper.CountTime(timeCount);
        } catch (DataAccessException e) {
            log.error("{}--->{}", CountTimeFail,e.getMessage());
            return new JsonResult(false, CountTimeFail.toString(), e.getMessage());
        }
        return new JsonResult(true, null, timeCounts);
    }
}
