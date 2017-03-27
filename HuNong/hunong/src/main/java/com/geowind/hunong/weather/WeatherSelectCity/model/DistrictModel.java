package com.geowind.hunong.weather.weatherselectcity.model;

/**
 * Created by logaxy on 16-11-23.
 */
public class DistrictModel {
    private String name;

    public DistrictModel() {
        super();
    }

    public DistrictModel(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "DistrictModel [name=" + name + "]";
    }

}

