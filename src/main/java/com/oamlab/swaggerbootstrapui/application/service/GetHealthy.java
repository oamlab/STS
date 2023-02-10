package com.oamlab.swaggerbootstrapui.application.service;

import com.oamlab.swaggerbootstrapui.domain.service.SelectUnixStampFromMysql;
import com.oamlab.swaggerbootstrapui.infrastructure.persistent.po.Healthy;

import java.io.IOException;
import java.util.Map;

public class GetHealthy {

    private static String getType(Object a) {
        return a.getClass().toString();
    }

    public static Healthy getHealthy() throws IOException {

        // 获取Mysql的UnixStamp
        SelectUnixStampFromMysql selectUnixStampFromMysql = new SelectUnixStampFromMysql();
        Map<Object, Object> b = selectUnixStampFromMysql.getUnixStamp();

        Map c = null;
        for (Object key : b.keySet()) {
            //System.out.println("key= " + key + " and value= " + b.get(key));
            c = (Map) b.get(key);
        }

        Healthy healthy = new Healthy();
        for (Object key : c.keySet()) {
            //System.out.println("key= " + key + " and value= " + c.get(key));

            if( key.equals("MysqlTimeStamp") ){

                String mysqlTimeStamp = c.get(key).toString();
                int mysqlTimeStampInt =new Integer( mysqlTimeStamp );

                String javaTimeStamp = Long.toString(System.currentTimeMillis()/1000L);
                int javaTimeStampInt =new Integer( javaTimeStamp );

                int healthyStatus = Math.abs(mysqlTimeStampInt - javaTimeStampInt);
                //System.out.println("===healthyStatus=" + healthyStatus);

                //可以输出JAVA进程的时间戳，也可以输出数据库时间戳与JAVA进程时间戳的差的绝对值
                healthy.setHealthy(Long.toString(healthyStatus));
                //healthy.setHealthy(Long.toString(javaTimeStampInt));
            }
        }
        return healthy;
    }
}