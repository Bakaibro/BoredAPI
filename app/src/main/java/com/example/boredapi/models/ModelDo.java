package com.example.boredapi.models;

import com.google.gson.annotations.SerializedName;

public class ModelDo {

    @SerializedName("key")
    private String key;

    @SerializedName("activity")
    private String activity;

    @SerializedName("price")
    private String price;

    @SerializedName("link")
    private String link;
    @SerializedName("type")
    private   String type;
    @SerializedName("accessibility")
    private   String accessibility;
    @SerializedName("participants")
    private String participants;
    @SerializedName("category")
    private String category;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}