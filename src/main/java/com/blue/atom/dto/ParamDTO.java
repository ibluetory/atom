package com.blue.atom.dto;

import lombok.Data;

/**
 * 参数解析返回
 *
 * @author liguowen
 */
@Data
public class ParamDTO {

    /**
     * 参数
     */
    private String param;

    /**
     * 文件名
     */
    private String fileName;
}
