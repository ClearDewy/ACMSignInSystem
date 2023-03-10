package com.cleardewy.acmsis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult<T> {
    private boolean success;
    private String message;
    private T data;

    public JsonResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
