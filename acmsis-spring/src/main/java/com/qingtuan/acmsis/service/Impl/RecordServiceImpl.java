package com.qingtuan.acmsis.service.Impl;

import com.qingtuan.acmsis.entity.JsonResult;
import com.qingtuan.acmsis.entity.Record;
import com.qingtuan.acmsis.entity.TimeCount;
import com.qingtuan.acmsis.mapper.RecordMapper;
import com.qingtuan.acmsis.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import static com.qingtuan.acmsis.constant.ErorrMessage.CountTimeFail;
import static com.qingtuan.acmsis.constant.ErorrMessage.TimeError;

/**
 * @Author: Qingtuan
 * @Date: 2022/10/13 19:24
 */
@Service
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
            return new JsonResult(false, TimeError.toString(), e.getMessage());
        }
        try {
            timeCounts = recordMapper.CountTime(timeCount);
        } catch (DataAccessException e) {
            return new JsonResult(false, CountTimeFail.toString(), e.getMessage());
        }
        return new JsonResult(true, null, timeCounts);
    }
}
