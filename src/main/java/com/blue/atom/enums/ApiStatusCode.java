package com.blue.atom.enums;

import lombok.Getter;

/**
 * @author zhoumb
 */
@Getter
public enum ApiStatusCode {

    /**
     * success
     */
    SUCCESS(200, "success"),
    /**
     * 当前用户未设置凭证
     */
    NO_VOUCHER(3004, "当前用户未设置凭证"),
    /**
     * 当前用户凭证密码错误
     */
    USER_VOUCHER_ERROR(3016, "当前用户凭证密码错误"),
    /**
     * 用户不存在
     */
    NO_USER(3005, "用户不存在"),
    /**
     * TOKEN为空
     */
    NO_TOKEN(3007, "token为空"),
    /**
     * token错误
     */
    TOKEN_ERROR(3008, "token错误"),
    /**
     * 登录超时
     */
    TOKEN_TIMEOUT(3009, "登录超时，请重新登录"),
    /**
     * 用户已退出当前系统
     */
    LOGOUT_APP(3010, "用户已退出当前系统"),
    /**
     * 用户已注销
     */
    USER_OFF_MSG(3014, "用户已注销，请联系管理员"),
    /**
     * 参数校验出错
     */
    VERIFY_ERROR(4000, "参数校验出错"),
    /**
     * SQL出错
     */
    SQL_ERROR(4100, "SQL出错"),
    /**
     * JSON转换出错
     */
    JSON_ERROR(4200, "JSON转换出错"),
    /**
     * 404
     */
    NOT_FOUND(404, "not_found"),
    /**
     * 400
     */
    METHOD_NOT_ALLOWED(405, "method_not_allowed"),
    /**
     * media_type_not_acceptable
     */
    MEDIA_TYPE_NOT_ACCEPTABLE(406, "media_type_not_acceptable"),
    /**
     * too_many_requests
     */
    TOO_MANY_REQUESTS(429, "too_many_requests"),
    /**
     * error
     */
    ERROR(500, "error");

    private final int code;
    private final String message;

    ApiStatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
