package com.blue.atom.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserPageParam {

    private long size;
    private long current;
}
