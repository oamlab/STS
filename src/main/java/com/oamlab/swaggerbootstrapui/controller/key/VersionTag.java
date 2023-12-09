package com.oamlab.swaggerbootstrapui.controller.key;

import com.oamlab.swaggerbootstrapui.application.service.GetHealthy;
import com.oamlab.swaggerbootstrapui.application.service.GetVersion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/**
 * @ Copyright (C), 2022-2023, OAMLab
 * @ Author:   Andy Yao
 * @ Date:     Created in 2023/12/08 22:01
 * @ Description:
 * @ Modified By：
 * @ Version: 1.0
 */
@Api(tags = "API版本号")
@RestController
@RequestMapping(value = "/api/v1")
public class VersionTag {

    @ApiOperation("获取API版本号")
    @GetMapping ("/version")
    public <list> list getVersion() throws IOException {

        list versionTag = (list) GetVersion.getVersion();
        return versionTag;
    }
}
