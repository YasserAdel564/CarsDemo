
package com.softxperttask.app.data.model;
import com.squareup.moshi.Json;

public class CarModel {
    @Json(name = "id")
    private int id;
    @Json(name = "brand")
    private String brand = null;
    @Json(name = "constractionYear")
    private String year = null;
    @Json(name = "isUsed")
    private Boolean isUsed = null;
    @Json(name = "imageUrl")
    private String imageUrl = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
