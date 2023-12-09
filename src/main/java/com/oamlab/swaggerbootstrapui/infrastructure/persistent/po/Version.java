package com.oamlab.swaggerbootstrapui.infrastructure.persistent.po;

public class Version {

    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    @Override
    public String toString() {
        return "Version{" +
                "version=" + version +
                '}';
    }
}
