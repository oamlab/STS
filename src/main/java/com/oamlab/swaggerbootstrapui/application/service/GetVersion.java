package com.oamlab.swaggerbootstrapui.application.service;

import com.oamlab.swaggerbootstrapui.infrastructure.persistent.po.Version;


public class GetVersion {

    private static String getType(Object a) {
        return a.getClass().toString();
    }

    public static Version getVersion() {

        Version versionTag = new Version();

        //1、定义一个手动写入的版本号，用于给开发人员跟踪交付的软件版本
        versionTag.setVersion("20231208-230601");

        return versionTag;
    }
}