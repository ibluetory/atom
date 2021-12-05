package com.blue.atom.dto;

import com.blue.atom.enums.ApiStatusCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 接口返回统一对象
 */
@Data
@ApiModel(value = "ResultDTO", description = "接口返回统一对象")
public class ResultDTO<T> implements Serializable {
    private static final long serialVersionUID = -6062295082491693691L;
    /**
     * 成功标志
     */
    @ApiModelProperty(value = "成功标志")
    private Boolean success;

    /**
     * 返回处理消息
     */
    @ApiModelProperty(value = "描述信息")
    private String message;

    /**
     * 返回代码
     */
    @ApiModelProperty(value = "状态码")
    private Integer code;

    /**
     * 返回数据对象 data
     */
    @ApiModelProperty(value = "响应数据")
    private T data;

    private ResultDTO() {
    }

    public static <T> ResultDTO<T> error(final int code, final String msg) {
        final ResultDTO<T> r = new ResultDTO<>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    public static <T> ResultDTO<T> error(final ApiStatusCode apiStatusCode) {
        final ResultDTO<T> r = new ResultDTO<>();
        r.setCode(apiStatusCode.getCode());
        r.setMessage(apiStatusCode.getMessage());
        r.setSuccess(false);
        return r;
    }

    public static <T> ResultDTO<T> error(final int code) {
        final ResultDTO<T> r = new ResultDTO<>();
        r.setCode(code);
        r.setSuccess(false);
        return r;
    }

    public static <T> ResultDTO<T> success(final T data) {
        final ResultDTO<T> r = new ResultDTO<>();
        r.setSuccess(true);
        r.setCode(ApiStatusCode.SUCCESS.getCode());
        r.setData(data);
        return r;
    }

    public static <T> ResultDTO<T> success() {
        final ResultDTO<T> r = new ResultDTO<>();
        r.setSuccess(true);
        r.setCode(ApiStatusCode.SUCCESS.getCode());
        return r;
    }
}
