package com.oamlab.swaggerbootstrapui.domain.common;

import com.oamlab.swaggerbootstrapui.infrastructure.persistent.po.Key;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


@Slf4j
public class SM4Utils {

    /**
     * SM4加密
     *
     * @param Key 包含加解密相关参数信息的实体
     * @return 处理结果
     */
    public static <key> key encrypt4Data(Key Key) {
        //指定的密钥
        String key = Key.getCryptKey();

        // 若为空，使用默认
        if (StringUtils.isBlank(key)) {
            key = "xxxxxxxXXxxxxxxx";
        }
        String data = Key.getPlainText();
        try {
            SymmetricCrypto sm4 = SmUtil.sm4(key.getBytes(CharsetUtil.CHARSET_UTF_8));
            String dataHex = sm4.encryptHex(data);
            Key.setCipherText(dataHex);
        } catch (Exception e) {
            log.error("加密数据异常，异常数据：" + data, e);
        }
        return (key) Key;
    }

    /**
     * SM4解密
     *
     * @param Key 包含加解密相关参数信息的实体
     * @return 处理结果
     */
    public static <key> key decrypt4Data(Key Key) {
        //指定的密钥
        String key = Key.getCryptKey();

        // 若为空，使用默认
        if (StringUtils.isBlank(key)) {
            key = "xxxxxxxXXxxxxxxx";
        }
        String dataHex = Key.getCipherText();
        try {
            SymmetricCrypto sm4 = SmUtil.sm4(key.getBytes(CharsetUtil.CHARSET_UTF_8));
            String data = sm4.decryptStr(dataHex);
            Key.setPlainText(data);
        } catch (Exception e) {
            log.error("解密数据异常，异常数据：" + dataHex, e);
        }
        return (key) Key;
    }
}
