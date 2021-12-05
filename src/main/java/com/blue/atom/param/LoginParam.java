package com.blue.atom.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginParam {

    private String username;

    private String mobile;

    private String password;
}
