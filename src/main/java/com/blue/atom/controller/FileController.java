package com.blue.atom.controller;

import com.blue.atom.annotation.SkipAuth;
import com.blue.atom.dto.ParamDTO;
import com.blue.atom.dto.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 文件控制器
 * </p>
 *
 * @author liguowen
 * @since 2021-11-23
 */
@Api(tags = "FileController")
@Controller
@RequestMapping("/file")
public class FileController {

    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @SkipAuth
    @ApiOperation(value = "txt参数解析")
    @RequestMapping(value = "/param")
    public @ResponseBody
    ResponseEntity txtToJson(@RequestParam MultipartFile file) throws IOException {
        String param = new String(file.getBytes(), StandardCharsets.UTF_8);
        ParamDTO paramDTO = new ParamDTO();
        paramDTO.setParam(param);
        paramDTO.setFileName(file.getOriginalFilename());
        return new ResponseEntity<>(ResultDTO.success(paramDTO), HttpStatus.OK);
    }
}
