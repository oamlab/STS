package com.oamlab.swaggerbootstrapui.application.service;

import com.oamlab.swaggerbootstrapui.domain.common.SM4Utils;
import com.oamlab.swaggerbootstrapui.infrastructure.persistent.po.Key;

import java.io.IOException;

public class DataDecrypt<keys> {

    private static String getType(Object a) {
        return a.getClass().toString();
    }

    public static Key dataDecrypt(String keyId, String cipherText) throws IOException {

        //根据keyId，获取秘钥
        Key key = GetKey.getKey(keyId);

        key.setCipherText(cipherText);
        key = SM4Utils.decrypt4Data(key);

        //防止加密秘钥泄露
        key.setCryptKey(null);
        key.setId(null);

        return key;
    }
}
