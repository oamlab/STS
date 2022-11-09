package com.oamlab.swaggerbootstrapui.application.service;

import com.oamlab.swaggerbootstrapui.domain.common.SM4Utils;
import com.oamlab.swaggerbootstrapui.infrastructure.persistent.po.Key;

import java.io.IOException;

public class DataEncrypt<keys> {

    private static String getType(Object a) {
        return a.getClass().toString();
    }

    public static Key dataEncrypt(String keyId, String plainText) throws IOException {

        Key key = GetKey.getKey(keyId);

        key.setPlainText(plainText);
        key = SM4Utils.encrypt4Data(key);

        //防止加密秘钥泄露
        key.setCryptKey(null);
        key.setId(null);

        return key;
    }
}
