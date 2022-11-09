package com.oamlab.swaggerbootstrapui.application.service;

import com.oamlab.swaggerbootstrapui.domain.service.SaveKeyToMysql;
import com.oamlab.swaggerbootstrapui.infrastructure.persistent.po.Key;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.util.Map;

public class MakeKey<keys> {

    private static String getType(Object a) {
        return a.getClass().toString();
    }

    public static Key makeKey() throws IOException {

        String randomStringKeyId = RandomStringUtils.randomAlphanumeric(64);
        String randomStringKey = RandomStringUtils.randomAlphanumeric(16);

        SaveKeyToMysql saveKeyToMysql = new SaveKeyToMysql();
        Map<Object, Object> b = saveKeyToMysql.saveKeys(randomStringKeyId,randomStringKey);

        System.out.println("===========" + b);

        Map c = null;
        for (Object key : b.keySet()) {
             //System.out.println("key= " + key + " and value= " + b.get(key));
            c = (Map) b.get(key);
        }

        Key keyPut = new Key();
        for (Object key : c.keySet()) {
             //System.out.println("key= " + key + " and value= " + c.get(key));

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

        //防止加密秘钥泄露
        keyPut.setId(null);
        return keyPut;
    }
}
