package com.itkang.itkang_utils.微信开发;

import java.io.Serializable;

/**
 * @author Mr.kang
 * Created by - 林夕
 * @date 2018/9/7 10:05
 *
 * 返回结果封装实体类
 */
public class Result implements Serializable {
    private Boolean success;
    private String message;

    public Result(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
