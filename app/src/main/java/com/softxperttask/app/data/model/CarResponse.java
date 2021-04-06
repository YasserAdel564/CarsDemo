
package com.softxperttask.app.data.model;

import android.content.Intent;

import com.squareup.moshi.Json;

import java.util.List;

public class CarResponse {

    @Json(name = "status")
    private int status;
    @Json(name = "data")
    private List<CarModel> data = null;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CarModel> getData() {
        return data;
    }

    public void setData(List<CarModel> data) {
        this.data = data;
    }
}
