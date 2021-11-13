package br.com.zonesoftware.apicombustivel.model;

import java.math.BigDecimal;

public class Route {

    private BigDecimal latitude;
    private BigDecimal longitude;

    public Route() {
    }

    public Route(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
