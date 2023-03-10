package com.cleardewy.acmsis.service;

import com.cleardewy.acmsis.entity.JsonResult;
import com.cleardewy.acmsis.entity.Record;
import com.cleardewy.acmsis.entity.TimeCount;

/**
 * @Author: cleardewy
 * @Date: 2022/10/13 19:19
 */

public interface RecordService {
    Integer AddRecord(Record record);

    JsonResult CountTime(TimeCount timeCount);
}
