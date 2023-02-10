package com.oamlab.swaggerbootstrapui.infrastructure.persistent.po;

public class Healthy {

    private String healthy;

    public String getHealthy() {
        return healthy;
    }

    public void setHealthy(String healthy) {
        this.healthy = healthy;
    }


    @Override
    public String toString() {
        return "Healthy{" +
                "healthy=" + healthy +
                '}';
    }
}
