package com.oamlab.swaggerbootstrapui.application.service;

import com.oamlab.swaggerbootstrapui.domain.service.SelectFromMysql;
import com.oamlab.swaggerbootstrapui.infrastructure.persistent.po.Key;

import java.io.IOException;
import java.util.*;

public class GetKey<keys> {

    private static String getType(Object a) {
        return a.getClass().toString();
    }

    public static Key getKey(String keyId) throws IOException {

        // 获取keyId对应的id,keyId,秘钥,
        SelectFromMysql selectFromMysql = new SelectFromMysql();
        Map<Object, Object> b = selectFromMysql.getKeys(keyId);

        Map c = null;
        for (Object key : b.keySet()) {
            // System.out.println("key= " + key + " and value= " + b.get(key));
            c = (Map) b.get(key);
        }

        Key keyPut = new Key();
        for (Object key : c.keySet()) {
            // System.out.println("key= " + key + " and value= " + c.get(key));

            if( key.equals("id") ){
                keyPut.setId(Integer.parseInt(c.get(key).toString()));
            }

            if( key.equals("keyid") ){
                // System.out.println("=======" + c.get(key).toString());
                keyPut.setKeyId(c.get(key).toString());
            }

            if( key.equals("cryptkey") ){
                keyPut.setCryptKey(c.get(key).toString());
            }

        }
        return keyPut;
    }
}
