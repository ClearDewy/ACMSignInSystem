package com.qingtuan.acmsis.service;

import com.qingtuan.acmsis.entity.JsonResult;
import com.qingtuan.acmsis.entity.Record;
import com.qingtuan.acmsis.entity.TimeCount;

/**
 * @Author: Qingtuan
 * @Date: 2022/10/13 19:19
 */

public interface RecordService {
    Integer AddRecord(Record record);

    JsonResult CountTime(TimeCount timeCount);
}
