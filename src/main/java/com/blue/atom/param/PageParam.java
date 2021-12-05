package com.blue.atom.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author liguowen
 */
@Data
public class PageParam {

    @ApiModelProperty(value = "页号", required = true)
    @NotNull
    private Long current;

    @ApiModelProperty(value = "分页大小", required = true)
    @NotNull
    private Long size;
}
