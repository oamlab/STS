package com.oamlab.swaggerbootstrapui.controller.key;

import com.oamlab.swaggerbootstrapui.application.service.GetHealthy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


/**
 * @ Copyright (C), 2022-2023, OAMLab
 * @ Author:   Andy Yao
 * @ Date:     Created in 2023/02/09 21:00
 * @ Description:
 * @ Modified By：
 * @ Version: 1.0
 */
@Api(tags = "API状态")
@RestController
@RequestMapping(value = "/api/v1")
public class Healthy {

    @ApiOperation("获取API健康状态")
    @GetMapping ("/healthy")
    public <list> list getHealthy() throws IOException {

        list healthy = (list) GetHealthy.getHealthy();
        return healthy;
    }
}
