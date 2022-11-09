package com.oamlab.swaggerbootstrapui.controller.key;

import com.oamlab.swaggerbootstrapui.application.service.GetKey;
import com.oamlab.swaggerbootstrapui.bean.Key;
import com.oamlab.swaggerbootstrapui.application.service.DataDecrypt;
import com.oamlab.swaggerbootstrapui.application.service.MakeKey;
import com.oamlab.swaggerbootstrapui.application.service.DataEncrypt;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @ Copyright (C), 2022-2022, OAMLab
 * @ Author:   Andy Yao
 * @ Date:     Created in 2022/11/8 20:21
 * @ Description:
 * @ Modified By：
 * @ Version: 1.0
 */
@Api(tags = "秘钥管理API")
@RestController
@RequestMapping(value = "/api/v1/key")
public class KeyController {


    @ApiOperation("制造秘钥")
    @PostMapping("/makeKey")
    public <list> list makeKey(@RequestBody @ApiParam(name="key",value="秘钥对象",required=true) Key key) throws IOException {

        list keys = (list) MakeKey.makeKey();
        return keys;
    }


    @ApiOperation("查询秘钥")
    @PostMapping("/getKey")
    public <list> list getKey(@RequestBody @ApiParam(name="keyId",value="秘钥id",type="String",required=true) Key key) throws IOException {

        String keyId = key.getKeyId();
        if (StringUtils.isNotBlank(keyId)) {
            list keys = (list) GetKey.getKey(keyId);
        }else {
            // 关闭查询秘钥
            return null;
        }
        // 关闭查询秘钥
        return null;
    }


    @ApiOperation("加密")
    @PostMapping("/dataEncrypt")
    public <list> list dataEncrypt(@RequestBody @ApiParam(name="keyId",value="秘钥id",type="String",required=true) Key key) throws IOException {

        String keyId = key.getKeyId();
        String plainText = key.getPlainText();
        if (StringUtils.isNotBlank(keyId) && StringUtils.isNotBlank(plainText)) {
            list keys = (list) DataEncrypt.dataEncrypt(keyId, plainText);
            return keys;
        }else {
            return null;
        }
    }

    @ApiOperation("解密")
    @PostMapping("/dataDecrypt")
    public <list> list dataDecrypt(@RequestBody @ApiParam(name="keyId",value="秘钥id",type="String",required=true) Key key) throws IOException {

        String keyId = key.getKeyId();
        String cipherText = key.getCipherText();
        if (StringUtils.isNotBlank(keyId) && StringUtils.isNotBlank(cipherText)) {
            list keys = (list) DataDecrypt.dataDecrypt(keyId, cipherText);
            return keys;
        }else {
            return null;
        }
    }
}
